package com.example.retailRevamp.Controller;

import com.example.retailRevamp.Model.BusinessModel;
import com.example.retailRevamp.Model.UserModel;
import com.example.retailRevamp.Service.BusinessService;
import com.example.retailRevamp.Service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CommonsLog
@RestController
@RequestMapping("/retail-revamp/business")
public class BusinessController {
    @Autowired
    public BusinessService businessService;
    @Autowired
    public UserService userService;


    @GetMapping("/home")
    public ResponseEntity<?> homepage() {
        return new ResponseEntity<String>("Business Access Denied", HttpStatus.FORBIDDEN);
    }
    @GetMapping("/business")
    public ResponseEntity<?> getAllBusiness() {
        List<BusinessModel> all = businessService.getAllBusiness();
        if (all != null && !all.isEmpty()) {
            return ResponseEntity.ok().body(all);
        } else {
            return ResponseEntity.status(404).body("Business Not found");
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllBusinessOfUser(@PathVariable int id) {
//        UserModel userModel=userService.findUserById(id);
        List<BusinessModel> all = businessService.getAllEntry(id);
        if (all != null && !all.isEmpty()) {
            return ResponseEntity.ok().body(all);
        } else {
            return ResponseEntity.status(404).body("Not found");
        }

    }

    @PostMapping("/{id}")
    public ResponseEntity<?> postBusinessOfUser(@RequestBody BusinessModel business, @PathVariable int id) {
        Optional<BusinessModel> entry = businessService.findEntry(business.getId());
        log.info("postBusinessOfUser businessEntry: "+entry);
        if (entry == null || entry.isEmpty()) {
            //create
            try {
                UserModel userModel = userService.findUserById(id);
                log.info("postBusinessOfUser user: "+userModel.getName());
                if (userModel != null && userModel.getName()!=null &&!userModel.getName().isEmpty()) {
                    log.info("postBusinessOfUser businessService: "+business.getName());
                    try{
                        businessService.saveEntry(business, userModel);
                    }catch (Exception e){
                        log.error(e.getMessage());
                        e.printStackTrace();
                        log.info("postBusinessOfUser user: "+userModel.getName());
                    }
                } else {
                    return ResponseEntity.status(404).body("User Not Found");
                }
                log.info("postBusinessOfUser business: "+business.getName());
//                return ResponseEntity.status(200).body(revampService.findEntry(user.getId()));
                return new ResponseEntity(businessService.findEntry(business.getId()), HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error in creating Business");
            }
        } else {
            return ResponseEntity.status(400).body("Business Already exist");
        }

//        return revampService.findEntry(user.getId());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBusiness(@PathVariable Integer id){
        Optional<BusinessModel> entry= businessService.findEntry(id);
        if(entry!=null && !entry.isEmpty()){
            return ResponseEntity.ok().body(entry);
        }else{
            return ResponseEntity.status(404).body("Not found");
        }
    }
    @PutMapping("/id/{id}/{businessId}")
    public ResponseEntity<?> updateBusiness(@PathVariable Integer id, @RequestBody BusinessModel businessModel,@PathVariable Integer businessId){
        UserModel user= userService.findUserById(id);
        if(user!=null){
            BusinessModel business=businessService.findBusinessById(businessId);
            if(business!=null){
//                business.setGstName(businessModel.getName());
//                business.setName(businessModel.getPassword());
//                business.setTurnOver(businessModel.getPassword());
//                business.setId(businessModel.getPassword());
                businessService.updateBusiness(businessModel);
            }else{
                return ResponseEntity.status(404).body("Business Not found");
            }
        }else{
            return ResponseEntity.status(404).body("User Not found");
        }
        return ResponseEntity.ok().body(userService.findEntry(id));
    }

    @DeleteMapping("/{id}/{businessId}")
    public ResponseEntity<?> deleteBusinessByIdOfUser(@PathVariable Integer id, @PathVariable Integer businessId) {
        UserModel user = userService.findUserById(id);
        if (user != null ) {
            businessService.deleteEntry(businessId,user);
        } else {
            return ResponseEntity.status(404).body("User Not found");
        }
        return ResponseEntity.ok().body("Business deleted");
    }


}
