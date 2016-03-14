package org.pw.rafalj.crm.utils.authentication;

/**
 * Created by rjozwiak on 2016-01-12.
 */
public class TokenTransfer {
    private final String token;

    public TokenTransfer(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}