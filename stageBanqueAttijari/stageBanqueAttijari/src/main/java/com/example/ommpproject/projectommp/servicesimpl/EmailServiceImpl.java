package com.example.ommpproject.projectommp.servicesimpl;

import com.example.ommpproject.projectommp.models.EmailAddress;
import com.example.ommpproject.projectommp.models.EmailRequest;
import com.example.ommpproject.projectommp.services.EmailService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${email.api.url}")
    private String emailApiUrl;  

   

    private static final String FIXED_CC_EMAIL = "chaouch.ahmed@attijaribank.com.tn"; 

    private final RestTemplate restTemplate = new RestTemplate();

	/*
	 * @Override public void sendEmailessai(EmailRequest emailRequest) { HttpHeaders
	 * headers = new HttpHeaders(); headers.set("Content-Type", "application/json");
	 * 
	 * HttpEntity<EmailRequest> request = new HttpEntity<>(emailRequest, headers);
	 * 
	 * ResponseEntity<String> response = restTemplate.exchange( emailApiUrl,
	 * HttpMethod.POST, request, String.class );
	 * 
	 * if (response.getStatusCode() == HttpStatus.OK) {
	 * 
	 * String toList = emailRequest.getTo().stream() .map(EmailAddress::getEmail)
	 * .collect(Collectors.joining(", "));
	 * 
	 * } else {
	 * 
	 * System.err.println("Erreur lors de l'envoi de l'email : " +
	 * response.getStatusCode()); } }
	 */
    
    
    @Override
    public void sendEmail(EmailRequest emailRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String jsonRequest = String.format(
            "{\"to\" : [{\"email\": \"%s\"}], \"cc\": [{\"email\": \"%s\"}], \"subject\": \"%s\", \"content\": {\"type\": \"text/html\", \"value\": \"%s\"}}",
            emailRequest.getTo(), FIXED_CC_EMAIL, emailRequest.getSubject(), emailRequest.getContent().getValue()
        );

        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            emailApiUrl,
            HttpMethod.POST,
            request,
            String.class
        );

		/*
		 * if (response.getStatusCode() == HttpStatus.OK) {
		 * saveEmailToFile(emailRequest.getTo(), emailRequest.getSubject(),
		 * emailRequest.getContent().getValue()); } else {
		 * System.err.println("Erreur lors de l'envoi de l'email : " +
		 * response.getStatusCode()); }
		 */
    }
    
    
    
    
    
    
    

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
      
        
    }
    public void saveEmailToFile(String to, String subject, String body) {
        try (FileWriter writer = new FileWriter("email_log.txt", true)) {
            writer.write("To: " + to + "\n");
            writer.write("CC: " + FIXED_CC_EMAIL + "\n");
            writer.write("Subject: " + subject + "\n");
            writer.write("Body: " + body + "\n");
            writer.write("-----\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   

   
    
}
