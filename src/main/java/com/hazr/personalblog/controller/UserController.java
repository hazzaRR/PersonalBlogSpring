package com.hazr.personalblog.controller;

import com.hazr.personalblog.dto.UserDTO;
import com.hazr.personalblog.service.UserService;
import org.springframework.web.bind.annotation.*;

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
}
