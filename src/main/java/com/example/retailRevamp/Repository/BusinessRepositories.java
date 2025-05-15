package com.example.retailRevamp.Repository;

import com.example.retailRevamp.Model.BusinessModel;
import com.example.retailRevamp.Model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BusinessRepositories extends MongoRepository<BusinessModel,Integer> {
    public BusinessModel findBusinessById(int id);
}
