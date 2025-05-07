package com.example.retailRevamp.Controller;

import com.example.retailRevamp.Model.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/retail-revamp")
public class RetailRevampController {
    HashMap<Integer, UserModel> hm=new HashMap<>();
    @GetMapping("/home")
    public String homepage(){
        return "Welcome to retail revamp";
    }
    @GetMapping
    public List<UserModel> getAllNames(){
        return new ArrayList<>(hm.values());
    }
//
    @PostMapping
    public String postName(@RequestBody UserModel user){
        if(!hm.containsKey(user.getId())) {
            hm.put(user.getId(), user);
            return "Successfully saved";
        }else{
            return "Already exist";
        }
    }

    @GetMapping("/id/{id}")
    public UserModel getName(@PathVariable Integer id){
        return hm.get(id);
    }


    @PutMapping("/id/{id}")
    public String updateName(@PathVariable Integer id,@RequestBody UserModel userModel){
        if(hm.containsKey(id)){
            hm.put(id,userModel);
            return hm.get(id).toString();
        }else{
            return "doesnot exist";
        }
    }

}
