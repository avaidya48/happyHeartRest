package com.example.happyheart.controller;

import com.example.happyheart.object.Appointment;
import com.example.happyheart.object.MedicalDetails;
import com.example.happyheart.object.User;
import com.example.happyheart.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class AppointmentController
{
    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/createAppointment")
    public String register(@RequestBody Appointment appointment) throws ExecutionException, InterruptedException {
        return firebaseService.createAppointment(appointment);
    }
    
    @GetMapping("/createAppointment")
    public List<Appointment> getLatestAppointments(@RequestHeader String email) throws ExecutionException, InterruptedException {
        return firebaseService.getLatestAppointments(email);
    }
    
    
}
