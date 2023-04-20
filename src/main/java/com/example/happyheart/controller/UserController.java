package com.example.happyheart.controller;

import com.example.happyheart.object.User;
import com.example.happyheart.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/login")
    public String login(@RequestBody User user) throws ExecutionException, InterruptedException {
        return firebaseService.login(user);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) throws ExecutionException, InterruptedException {
        return firebaseService.register(user);
    }




}
