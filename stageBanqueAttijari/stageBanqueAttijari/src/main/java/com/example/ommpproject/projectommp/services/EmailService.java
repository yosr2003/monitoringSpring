package com.example.ommpproject.projectommp.services;

import com.example.ommpproject.projectommp.models.EmailRequest;

public interface EmailService {
	public void sendEmail(String to, String subject, String text) ;
    public void saveEmailToFile(String to, String subject, String body) ;
    void sendEmail(EmailRequest emailRequest);
	//void sendEmailessai(EmailRequest emailRequest);
}
