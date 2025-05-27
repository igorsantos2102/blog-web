package com.blogMaker.blogServer.dto;

public class AuthenticationResponse {

    private String jwtToken;
    private String username;
    private String name;

    // Construtor para ambas as propriedades
    public AuthenticationResponse(String jwtToken, String username, String name) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.name = name;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getName() { 
    	return name; 
    }
    
    public void setName(String name) { 
    	this.name = name; 
    }
}