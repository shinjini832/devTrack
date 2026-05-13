package com.devtrack.controller;

import com.devtrack.dto.LoginRequest;
import com.devtrack.dto.LoginResponse;
import com.devtrack.model.User;
import com.devtrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println(user);
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return userService.loginUser(request);
    }
}