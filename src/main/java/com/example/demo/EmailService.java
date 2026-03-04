package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendWelcomeEmail(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Welcome to Task Tracker!");
        message.setText("Hello " + username + ",\n\n" +
                "Welcome to FlowState. We are so excited to have you on board!\n\n" +
                "You can now securely log in and start organizing your life.\n\n" +
                "Best regards,\n" +
                "" +
                "FlowState Team");

        mailSender.send(message);
    }
}
