package com.hpl.nownew;

import com.hpl.nownew.MyBean.Result;
import com.hpl.nownew.MyBean.Token;
import com.hpl.nownew.MyBean.User;
import com.hpl.nownew.mapping.MethodInfoDao;
import com.hpl.nownew.mapping.TokenMapper;
import com.hpl.nownew.mapping.UserMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/demo")
@CrossOrigin(origins = "*")
public class DemoController {

    /*
    处方
    病人
    疾病
    治疗
    症状
    详情
     */

    //@Autowired  这里用Autowired也可以，不过有红色下划线，但也可以运行的。
    @Resource
    private MethodInfoDao methodInfoDao;
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Autowired
    private POIUtil poiUtil;
    //@Autowired
    @Resource
    private UserMapper userMapper;
    //@Autowired
    @Resource
    private TokenMapper tokenmapper;


    @PostMapping("/findByName")
    public List<MethodInfo> findByName(String name){
        List<MethodInfo> k = methodInfoDao.findByName(name);
        System.out.println(k);
         return k;
    }

    @GetMapping("/showAll")  // 展示所有的处方信息
    public List<MethodInfo> showAll() {
        System.out.println("123");
        List<MethodInfo> k = methodInfoDao.getMethodInfo();
        for(int i=0;i<k.size();i++){
            System.out.println(k.get(i));
        }
        return k;
    }

    @PostMapping("/insert")  // 插入一条处方
    public void insert(MethodInfo methodInfo){
        methodInfoDao.insertMethodInfo(methodInfo);
    }

    @PostMapping("/insertBatch")  // 批量插入处方，使用excel导入
    public void insertBatch(MultipartFile uploadFile) throws IOException {
           List<String[]> list = poiUtil.readExcel(uploadFile);
           List<MethodInfo> data = new ArrayList<>();
           long b = System.currentTimeMillis();
               for (int i = 0, n = list.size(); i < n; i++) {
                   MethodInfo methodInfo = new MethodInfo();
                   methodInfo.setPatientName(list.get(i)[0]);
                   methodInfo.setPatientSex(list.get(i)[1]);
                   methodInfo.setPatientAge(Integer.parseInt(list.get(i)[2]));
                   methodInfo.setDoctorName(list.get(i)[3]);
                   methodInfo.setDisease(list.get(i)[4]);
                   methodInfo.setCure(list.get(i)[5]);
                   methodInfo.setSymptom(list.get(i)[6]);
                   methodInfo.setMethodName(list.get(i)[7]);
                   methodInfo.setMethodDetail(list.get(i)[8]);
                   data.add(methodInfo);
               }
           methodInfoDao.insertBatch(data);
           System.out.println(System.currentTimeMillis()-b);
    }

    @PostMapping("/register")  // 用户注册
    public String register(User user){
        user.setName("..");
        User now = userMapper.findByUserId(user.getUserid());
        if( now!=null) return "改号码已经注册";
        userMapper.addUser(user);
        return "注册成功";
    }
    @GetMapping("/login")    // 登入
    public String login(User user, HttpServletRequest request, HttpServletResponse response)throws Exception {
        return yanzheng(user,request,response).getMsg();
    }
    @ResponseBody
    @GetMapping("/yanzheng")
    public Result yanzheng(User resqUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //创建返回信息对象
        System.out.println(request.getRequestURI());
        Result result = new Result();
        if( !tokenInterceptor.preHandle(request,response,new Object()) ){
            result.setMsg("被拦截了");
            return result;
        }


        //判断用户信息为空
        if ("".equals(resqUser.getName()) || "".equals(resqUser.getPassword())) {
            result.setMsg("传入的用户名/密码为空！");
            return result;
        }

        //根据客户用户名查找数据库的usre对象
        User myUser = userMapper.findByUserName(resqUser.getName());
        //判断用户不存在
        if (null == myUser) {
            result.setMsg("用户不存在");
            return result;
        }
        //判断用户不存在
        if (!resqUser.getPassword().equals(myUser.getPassword())) {
            result.setMsg("密码不正确");
            return result;
        }

        //根据数据库的用户信息查询Token
        Token token = tokenmapper.findByUserId(myUser.getUserid());
        //为生成Token准备
        String TokenStr = "";
        Date date = new Date();
        int nowtime = (int) (date.getTime() / 1000);
        //生成Token
        TokenStr = creatToken(myUser, date);
        if (null == token) {
            //第一次登陆
            token = new Token();
            token.setToken(TokenStr);
            token.setBuildtime(nowtime);
            token.setUserid(myUser.getUserid());
            tokenmapper.addToken(token);
        }else{
            //登陆就更新Token信息
            TokenStr = creatToken(myUser, date);
            token.setToken(TokenStr);
            token.setBuildtime(nowtime);
            tokenmapper.updataToken(token);
        }
        //返回Token信息给客户端
        result.setFlag(true);
        result.setMsg("登录成功");
        result.setToken(TokenStr);
        return result;
    }

    private String creatToken(User user, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT") // 设置header
                .setHeaderParam("alg", "HS256").setIssuedAt(date) // 设置签发时间
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3))
                .claim("userid",String.valueOf(user.getUserid()) ) // 设置内容
                .setIssuer("lws")// 设置签发人
                .signWith(signatureAlgorithm, "dahao"); // 签名，需要算法和key
        String jwt = builder.compact();
        return jwt;
    }

}
