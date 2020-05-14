package org.sid.security;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private final String role;

    public AuthenticationResponse(String jwt,String role) {
        this.role = role;
		this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
    public String  getRole(){
    	return role;
    }
}