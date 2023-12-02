package com.hazr.personalblog.dto;

public class RegistrationDTO {
    private String username;
    private String password;

    private String firstname;

    private String surname;

    private String email;

    private String profilePicURL;


    public RegistrationDTO() {
        super();
    }

    public RegistrationDTO(String username, String password, String firstname, String surname, String email, String profilePicURL) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.profilePicURL = profilePicURL;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public String toString() {
        return "Registration info: username: " + this.username + " password: " + this.password;
    }

}
