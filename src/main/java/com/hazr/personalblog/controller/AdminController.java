package com.hazr.personalblog.controller;


import com.hazr.personalblog.dto.RegistrationDTO;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AuthenticationService authenticationService;

    public AdminController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register-author")
    public User registerAuthor (@RequestBody RegistrationDTO body) {
        return authenticationService.registerAuthor(body.getUsername(), body.getPassword(), body.getFirstname(), body.getSurname(), body.getEmail(), body.getProfilePicURL());

    }
}
