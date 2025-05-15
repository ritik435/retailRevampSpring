package com.example.retailRevamp.Service.Impl;

import com.example.retailRevamp.Model.UserModel;
import com.example.retailRevamp.Repository.UserRepositories;
import com.example.retailRevamp.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    public UserRepositories userRepositories;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    public List<UserModel> getUsersWithSentiment(){
        Query query=new Query();
        query.addCriteria(Criteria.where("sentiment").is(true));
        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        List<UserModel> users=mongoTemplate.find(query,UserModel.class);
        return users;
    }

}
