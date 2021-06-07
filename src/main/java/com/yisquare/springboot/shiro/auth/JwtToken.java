package com.yisquare.springboot.shiro.auth;



import org.apache.shiro.authc.AuthenticationToken;


public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = -3317557159974645241L;
    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal(){
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
