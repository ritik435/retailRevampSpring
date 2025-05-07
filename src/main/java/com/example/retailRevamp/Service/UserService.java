package com.example.retailRevamp.Service;

import com.example.retailRevamp.Model.UserModel;
import com.example.retailRevamp.Repository.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    public UserRepositories userRepositories;

    public void saveEntry(UserModel userModel){
        userRepositories.save(userModel);
    }

    public List<UserModel> getAllEntry(){
        return userRepositories.findAll();
    }

    public Optional<UserModel> findEntry(int id){
        userRepositories.findById(id);
        return userRepositories.findById(id);
    }

    public boolean deleteEntry(int id){
        userRepositories.deleteById(id);
        return true;
    }
    public UserModel findUserById(int id){
//        userRepositories.findById(id);
        return userRepositories.findUserById(id);
    }

}
