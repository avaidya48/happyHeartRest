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

    @GetMapping("/getUserDetails")
    public User getUserDetails(@RequestHeader String name) throws ExecutionException, InterruptedException {
        return firebaseService.getUserDetails(name);
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return firebaseService.saveUserDetails(user);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return firebaseService.saveUserDetails(user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestHeader String userName) throws ExecutionException, InterruptedException {
        return firebaseService.deleteUser(userName);
    }

    @GetMapping("/findGeofenceRadius")
    public Integer findRadius(@RequestHeader String userName) throws ExecutionException, InterruptedException {
        return firebaseService.findRadius(userName);
    }



}
