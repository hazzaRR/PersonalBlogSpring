package com.hazr.personalblog.dto;

public class UpdateUserDetailsDTO {

    private String username;

    private String email;

    private String password;

    private String firstname;

    private String surname;

    public UpdateUserDetailsDTO(String username, String email, String password, String firstname, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
    }

    public UpdateUserDetailsDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
