package com.example.happyheart.controller;

import com.example.happyheart.object.MedicalDetails;
import com.example.happyheart.object.User;
import com.example.happyheart.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class MedicalDetailsController
{
    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/createMedicalDetails")
    public String createMedicalDetails(@RequestBody MedicalDetails medicalDetails) throws ExecutionException, InterruptedException {
        return firebaseService.createLatestMedicalDetails(medicalDetails);
    }
    
    @GetMapping("/getLatestMedicalDetails")
    public List<MedicalDetails> getLatestMedicalDetails(@RequestHeader String username) throws ExecutionException, InterruptedException, ParseException
    {
        return firebaseService.getLatestMedicalDetails(username);
    }

    @GetMapping("/getAllMedicalDetails")
    public List<MedicalDetails> getAllMedicalDetails(@RequestHeader String username) throws ExecutionException, InterruptedException, ParseException
    {
        return firebaseService.getAllMedicalDetails(username);
    }
}
