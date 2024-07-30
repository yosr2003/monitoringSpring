package com.example.ommpproject.projectommp.controller;

import com.example.ommpproject.projectommp.models.EmailRequest;
import com.example.ommpproject.projectommp.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public void sendEmail(@RequestBody EmailRequest emailRequest, @RequestHeader("Authorization") String authorization) {
        //emailService.sendEmail(emailRequest);
        emailService.saveEmailToFile(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getContent().getValue());
        
    }

}
