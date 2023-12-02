package com.hazr.personalblog.controller;


import com.hazr.personalblog.dto.LoginDTO;
import com.hazr.personalblog.dto.LoginResponseDTO;
import com.hazr.personalblog.dto.RegistrationDTO;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getFirstname(), body.getSurname(), body.getEmail(), body.getProfilePicURL());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO body) throws LoginException {

        LoginResponseDTO loginResponse = authenticationService.loginUser(body.getUsername(), body.getPassword());


        return ResponseEntity.ok(loginResponse);
    }


    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleAuthenticationException(LoginException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
