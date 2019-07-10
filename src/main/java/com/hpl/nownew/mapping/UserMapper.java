package com.hpl.nownew.mapping;

import com.hpl.nownew.MyBean.User;

public interface UserMapper {
    void addUser(User user);
 //   void updataUser(User user);
    User findByUserId(int id);
    User findByUserName(String name);
}
