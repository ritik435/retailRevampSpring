package com.example.retailRevamp.Repository;

import com.example.retailRevamp.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl{

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserModel> getUsersWithSentiment() {
        Query query = new Query();
        query.addCriteria(Criteria.where("sentiment").is(true));
        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        List<UserModel> users = mongoTemplate.find(query, UserModel.class);
        return users;
    }
}
