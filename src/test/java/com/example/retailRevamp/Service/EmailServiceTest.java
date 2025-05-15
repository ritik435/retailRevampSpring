package com.example.retailRevamp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
    void testEmailService(){
        emailService.sendEmail("aritik093@gmail.com","Hello this is Test","Test is successful");
    }
}
