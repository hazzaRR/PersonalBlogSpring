package com.hazr.personalblog.dto;

public class LoginResponseDTO {

    private String username;
    private String jwt;

    public LoginResponseDTO() {

    }

    public LoginResponseDTO(String username, String jwt) {
        this.username = username;
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
