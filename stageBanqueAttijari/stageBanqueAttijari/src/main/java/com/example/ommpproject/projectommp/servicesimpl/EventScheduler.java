package com.example.ommpproject.projectommp.servicesimpl;

import com.example.ommpproject.projectommp.models.Events;
import com.example.ommpproject.projectommp.repositories.EventTypeMappingRepository;
import com.example.ommpproject.projectommp.repositories.EventsRepository;
import com.example.ommpproject.projectommp.services.EmailService;
import com.example.ommpproject.projectommp.models.EmailContent;
import com.example.ommpproject.projectommp.models.EmailRequest;
import com.example.ommpproject.projectommp.models.EventTypeMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class EventScheduler {

    @Autowired
    private EventsRepository eventRepository;

    @Autowired
    private EventTypeMappingRepository eventTypeMappingRepository;
    @Autowired
    private EmailService emailService;

    // Cron job pour exécuter tous les jours à 8h
    @Scheduled(cron = "0 0 8 * * ?")
    //@Scheduled(cron = "0 * * * * ?")
    //@Scheduled(cron = "0 0 10 * * ?")
    
	/*
	 * 0 - Second (0 seconds) 0 - Minute (0 minutes past the hour) 10 - Hour (10 AM)
	 * - Day of the month (every day) - Month (every month) ? - Day of the week (no
	 * specific day)
	 */
    
    public void reportFailedEvents() {
    	System.out.println("Cron job started...");

        List<Events> allEvents = eventRepository.findAll();
        StringBuilder failedEventsText = new StringBuilder("Failed Events:\n");

        for (Events event : allEvents) {
            EventTypeMapping mapping = eventTypeMappingRepository.findByEventType(event.getEventType());
            if (mapping != null && "fail".equalsIgnoreCase(mapping.getDescription())) {
                failedEventsText.append(event.toString()).append("\n");
            }
        }

        if (failedEventsText.length() > "Failed Events:\n".length()) {
            String failedEventsReport = failedEventsText.toString();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("failed_events_report.txt", true))) {
                writer.write(failedEventsReport);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Create an EmailRequest object
            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setTo("chaouch.ahmed@attijaribank.com.tn"); 
            emailRequest.setSubject("Failed Events Report");
            
            // Create and set EmailContent
            EmailContent emailContent = new EmailContent();
            emailContent.setType("text/html");
            emailContent.setValue(failedEventsReport);
            emailRequest.setContent(emailContent);

            // Send the email using the EmailService
            emailService.sendEmail(emailRequest);
        }

        System.out.println("Cron job finished.");
    }

    public void testReportFailedEvents() {
        reportFailedEvents();
    }
}
