package com.hazr.personalblog.dto;

public class UserDTO {

    private String username;

    private String email;

    private String firstname;

    private String surname;

    private byte[] profilePicture;



    public UserDTO(String username, String email, String firstname, String surname, byte[] profilePicture) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
        this.profilePicture = profilePicture;
    }

    public UserDTO(String username, String email, String firstname, String surname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
    }

    public UserDTO() {
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

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
