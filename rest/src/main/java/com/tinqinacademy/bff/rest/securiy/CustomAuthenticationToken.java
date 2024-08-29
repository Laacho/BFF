package com.tinqinacademy.bff.rest.securiy;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private final MyUser myUser;

    public CustomAuthenticationToken( MyUser myUser) {
        super(myUser.getAuthorities());
        this.myUser = myUser;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return myUser.getAuthorities();
    }

    @Override
    public Object getPrincipal() {
        return myUser.getUsername();
    }
}
