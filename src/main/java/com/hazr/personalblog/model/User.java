package com.hazr.personalblog.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;


    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;


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

    public User(Long userId, String username, String password, Set<Role> authorities, String firstname, String surname, String email, String profilePicURL) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.profilePicURL = profilePicURL;
    }


    public User(String username, String password, Set<Role> authorities, String firstname, String surname, String email, String profilePicURL) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }


}
