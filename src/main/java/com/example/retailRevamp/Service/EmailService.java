package com.example.retailRevamp.Service;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
//            String htmlContent = "<h3>Welcome to our app</h3>" +
//                    "<p>Thank you for your coming.</p>" +
//                    "<p><strong>User ID:</strong> " + to + "</p>" +
//                    "<p><strong>Amount:</strong> $" + "amount" + "</p>";
//            message.setText(htmlContent);
            message.setText(body);
            log.info("email Message : " + message);
            mailSender.send(message);
        }catch (Exception e){
            log.error("Error in sending mail to : "+to);
            e.printStackTrace();
        }
    }
}
