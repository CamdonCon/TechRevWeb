package com.str.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.str.model.QuoteRequest;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendQuoteNotification(QuoteRequest quote) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("youremail@gmail.com");
        message.setSubject("New Repair Quote Request");
        message.setText("New Quote Request:\n\n" +
                "Device Type: " + quote.getDeviceType() + "\n" +
                "Brand: " + quote.getBrand() + "\n" +
                "Model: " + quote.getModel() + "\n" +
                "Issue: " + quote.getIssue());

        mailSender.send(message);
    }
}