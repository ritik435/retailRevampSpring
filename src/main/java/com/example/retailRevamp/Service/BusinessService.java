package com.example.retailRevamp.Service;

import com.example.retailRevamp.Model.BusinessModel;
import com.example.retailRevamp.Model.UserModel;
import com.example.retailRevamp.Repository.BusinessRepositories;
import org.apache.commons.logging.Log;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class BusinessService {
    private static final Log log = LogFactory.getLog(BusinessService.class);
    @Autowired
    public BusinessRepositories businessRepositories;
    @Autowired
    public UserService userService;

    @Transactional
    public void saveEntry(BusinessModel businessModel , UserModel userModel){
        log.info("saveEntry business: "+businessModel.getName() +" userModel: "+userModel);
        System.out.println("saveEntry business: "+businessModel.getName() +" userModel: "+userModel);
        BusinessModel saved = businessRepositories.save(businessModel);
        log.info("saveEntry saved: "+saved.getName());
        userModel.getBusinessList().add(saved);
//        saved.setName(null);
        userService.saveEntry(userModel);
        log.info("saveEntry saved: "+userModel);
    }
    public void updateBusiness(BusinessModel businessModel){
        businessRepositories.save(businessModel);
    }

    public List<BusinessModel> getAllEntry(int id){
        UserModel userModel=userService.findUserById(id);
        return userModel.getBusinessList();
//        return businessRepositories.findAll();
    }
    public List<BusinessModel> getAllBusiness(){
        return businessRepositories.findAll();
    }

    public Optional<BusinessModel> findEntry(int id){
        businessRepositories.findById(id);
        return businessRepositories.findById(id);
    }

    public BusinessModel findBusinessById(int id){
        return businessRepositories.findBusinessById(id);
    }
    public boolean deleteEntry(int id, UserModel user){
        user.getBusinessList().removeIf(x-> x.getId()==id);
        userService.saveEntry(user);
        businessRepositories.deleteById(id);
        return true;
    }
}
