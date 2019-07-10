package com.hpl.nownew.mapping;

import com.hpl.nownew.MyBean.Token;

public interface TokenMapper {
    void addToken(Token token);
    void updataToken(Token token);
    Token findByUserId(int userid);
}
