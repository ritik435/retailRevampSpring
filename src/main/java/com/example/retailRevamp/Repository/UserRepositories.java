package com.example.retailRevamp.Repository;

import com.example.retailRevamp.Model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepositories extends MongoRepository<UserModel,Integer> {
    public UserModel findUserById(int id);
}
