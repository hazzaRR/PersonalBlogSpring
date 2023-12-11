package com.hazr.personalblog.dto;

import java.util.List;

public class LoginResponseDTO {

    private String username;
    private String token;

    private List<String> roles;

    private byte[] profilePicture;

    public LoginResponseDTO() {

    }

    public LoginResponseDTO(String username, String token, List<String> roles) {
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    public LoginResponseDTO(String username, String token, List<String> roles, byte[] profilePicture) {
        this.username = username;
        this.token = token;
        this.roles = roles;
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
