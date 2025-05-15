package com.example.retailRevamp.Controller;

import com.example.retailRevamp.Model.UserModel;
import com.example.retailRevamp.Service.IUserService;
import com.example.retailRevamp.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/retail-revamp")
public class UserControllerv2 {
    @Autowired
    public IUserService userService;
    @Autowired
    WeatherService weatherService;


    @GetMapping("/home")
    public ResponseEntity<?> homepage(){
        return new ResponseEntity<String>("Access Denied", HttpStatus.FORBIDDEN) ;
    }
    @GetMapping("/hello")
    public ResponseEntity<?> hello(){

        String response="hi Ritik , "+weatherService.getTempInC("India");
        System.out.println(response);
        return new ResponseEntity<String>(response, HttpStatus.OK) ;
    }

    @GetMapping
    public ResponseEntity<?> getAllNames(){
        List<UserModel> all= userService.getAllEntry();
        if(all!=null && !all.isEmpty()){
            return ResponseEntity.ok().body(all);
        }else{
            return ResponseEntity.status(404).body("Not found");
        }

    }

    @PostMapping
    public ResponseEntity<?> postName(@RequestBody UserModel user){
        Optional<UserModel> entry= userService.findEntry(user.getId());
        if(entry==null || entry.isEmpty()){
            //create
            try {
                userService.saveEntry(user);
//                return ResponseEntity.status(200).body(revampService.findEntry(user.getId()));
                return new ResponseEntity(userService.findEntry(user.getId()), HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.status(500).body("Error in creating user");
            }
        }else{
            return ResponseEntity.status(400).body("User Already exist");
        }

//        return revampService.findEntry(user.getId());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getName(@PathVariable Integer id){
        Optional<UserModel> entry= userService.findEntry(id);
        if(entry!=null && !entry.isEmpty()){
            return ResponseEntity.ok().body(entry);
        }else{
            return ResponseEntity.status(404).body("Not found");
        }
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateName(@PathVariable Integer id, @RequestBody UserModel userModel){
        UserModel user= userService.findUserById(id);
        if(user!=null){
            user.setName(userModel.getName());
            user.setPassword(userModel.getPassword());
            user.setEmail(userModel.getEmail());
            user.setSentiment(userModel.getSentiment());
            userService.saveEntry(user);
        }else{
            return ResponseEntity.status(404).body("Not found");
        }
        return ResponseEntity.ok().body(userService.findEntry(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        Optional<UserModel> entry= userService.findEntry(id);
        if(entry!=null && !entry.isEmpty()){
            userService.deleteEntry(id);
        }else{
            return ResponseEntity.status(404).body("Not found");
        }
        return ResponseEntity.ok().body("User deleted");
    }

    @GetMapping("/get-users-with-sentiment")
    public ResponseEntity<?> getUserWithSentiment(){
        List<UserModel> response=userService.getUsersWithSentiment();
        System.out.println(response);
        return new ResponseEntity<List<UserModel>>(response, HttpStatus.OK) ;
    }
}
