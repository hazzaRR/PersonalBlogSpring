package com.hazr.personalblog.controller;

import com.hazr.personalblog.dto.UpdateUserDetailsDTO;
import com.hazr.personalblog.dto.UserDTO;
import com.hazr.personalblog.exception.IncorrectPasswordException;
import com.hazr.personalblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.LoginException;
import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{username}")
    public UserDTO fetchUserDetails(@PathVariable String username) {
        return userService.getUserDetails(username);
    }

    @PostMapping("/{username}")
    public UserDTO updateUserDetails(@PathVariable String username, @RequestPart("userDetails") UpdateUserDetailsDTO userDetailsDTO, @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture) throws LoginException, IOException {
        return userService.updateUserDetails(username, userDetailsDTO, profilePicture);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handleIncorrectPasswordException(IncorrectPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
