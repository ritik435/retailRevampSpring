package com.example.retailRevamp.Scheduler;

import com.example.retailRevamp.Model.UserModel;
import com.example.retailRevamp.Service.EmailService;
import com.example.retailRevamp.Service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserScheduler {

    private static final Log log = LogFactory.getLog(UserScheduler.class);
    @Autowired
    EmailService emailService;
    @Autowired
    IUserService userService;

    @Scheduled(cron="0 0 10 ? * SUN")
    void fetchSentimentsAndMail(){
        List<UserModel> userModelList=userService.getUsersWithSentiment();
        for(UserModel user : userModelList){
            log.info("user to send mail : "+user.getEmail());
            emailService.sendEmail(user.getEmail(),"Regarding subscription of our new product","this is to inform you that we have launched our new product! ");
        }
    }
}
