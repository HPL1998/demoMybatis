package com.hpl.nownew.MyBean;

public class Token {
    private int tokenid,userid,buildtime;
    private String token;
    public int getTokenid() {
        return tokenid;
    }
    public void setTokenid(int tokenid) {
        this.tokenid = tokenid;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public int getBuildtime() {
        return buildtime;
    }
    public void setBuildtime(int buildtime) {
        this.buildtime = buildtime;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public String toString() {
        return "Token [tokenid=" + tokenid + ", userid=" + userid + ", buildtime=" + buildtime + ", token=" + token + "]";
    }
}

