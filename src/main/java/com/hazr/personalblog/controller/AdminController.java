package com.hazr.personalblog.controller;


import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.dto.RegistrationDTO;
import com.hazr.personalblog.exception.EmailAlreadyTakenException;
import com.hazr.personalblog.exception.UsernameAlreadyTakenException;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.service.AuthenticationService;
import com.hazr.personalblog.service.AzureBlobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final AuthenticationService authenticationService;
    private final AzureBlobService azureBlobService;

    public AdminController(AuthenticationService authenticationService, AzureBlobService azureBlobService) {
        this.authenticationService = authenticationService;
        this.azureBlobService = azureBlobService;
    }

    @PostMapping("/register-author")
    public String registerAuthor (@RequestPart("authorDetails") RegistrationDTO body, @RequestPart(value = "profilePicture", required = false) MultipartFile image_file) {
        try {
            User user;

            user = authenticationService.registerAuthor(body.getUsername(), body.getPassword(), body.getFirstname(), body.getSurname(), body.getEmail(), null);
            if (image_file != null) {

                String fileName = azureBlobService.upload(body.getUsername() + "_profilePicture", image_file);
                user.setProfilePicURL(fileName);
            }

            return "User successfully created with username: " + user.getUsername();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<String> handleEmailExistsException(EmailAlreadyTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<String> handleUsernameExistsException(UsernameAlreadyTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
