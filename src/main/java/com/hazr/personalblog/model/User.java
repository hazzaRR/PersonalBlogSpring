package com.hazr.personalblog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;


    @Column(name = "username", nullable = false, unique = true)
    private String username;


    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;


    @Column(name = "email")
    private String email;



    @Column(name = "profile_picture")
    private String profilePicURL;

    protected User() {
    }

    public User(Long userId, String username, String firstname, String surname, String email, String profilePicURL) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.profilePicURL = profilePicURL;
    }

    public User(String username, String firstname, String surname, String email, String profilePicURL) {
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.profilePicURL = profilePicURL;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
