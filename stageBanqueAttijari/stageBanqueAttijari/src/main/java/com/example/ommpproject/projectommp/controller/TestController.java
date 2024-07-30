package com.example.ommpproject.projectommp.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ommpproject.projectommp.services.EmailService;
import com.example.ommpproject.projectommp.servicesimpl.EventScheduler;

@RestController  
@CrossOrigin("*")
public class TestController {

  

    @Autowired
    private EventScheduler eventScheduler;
    @Autowired
    private EmailService emailService;

   // @GetMapping("/test-email")
    //public String testEmail() {
    	//eventScheduler.reportFailedEvents();
        //return "Email sent!";
    //}
    //@GetMapping("/send-test-email")
    //public String sendTestEmail(@RequestParam String to) {
     //   emailService.sendEmail(to, "Test Email", "This is a test email sent from Spring Boot.");
       // return "Test email sent!";
    //}
    
    @GetMapping("/test-failed-events")
    public String testFailedEvents() {
        eventScheduler.testReportFailedEvents();
        return "Test triggered successfully";
    }
    @PostMapping("/send")
    public void sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.saveEmailToFile(to, subject, body);
    }
    @PostMapping("/sendEmail")
    public void sendEmail2(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
    }
}
