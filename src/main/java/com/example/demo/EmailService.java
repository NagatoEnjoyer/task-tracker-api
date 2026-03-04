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

        //Simulating the email in the server console
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n========== SIMULATED EMAIL SENT ==========");
        System.out.println("TO: " + toEmail);
        System.out.println("FROM: FlowState Team <hello@flowstate.app>");
        System.out.println("SUBJECT: Welcome to FlowState!");
        System.out.println("---------------------------------------------");
        System.out.println("Hello " + username + ",\n");
        System.out.println("Welcome to FlowState. We are so excited to have you on board!");
        System.out.println("You can now securely log in, get in the zone, and start organizing your life.\n");
        System.out.println("Stay focused,");
        System.out.println("The FlowState Team");
        System.out.println("=============================================\n");

        //This is the proper email sending, but the free tier in Render blocks sending emails

//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(toEmail);
//        message.setSubject("Welcome to Task Tracker!");
//        message.setText("Hello " + username + ",\n\n" +
//                "Welcome to FlowState. We are so excited to have you on board!\n\n" +
//                "You can now securely log in and start organizing your life.\n\n" +
//                "Best regards,\n" +
//                "" +
//                "FlowState Team");
//
//        mailSender.send(message);
    }
}
