package com.Commercial.commercial.Service.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,String subject,String body){
        String EmailSender="ghadamhenni123@gmail.com";
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(EmailSender);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("message sent successfully...");
    }
}
