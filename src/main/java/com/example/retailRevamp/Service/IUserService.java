package com.example.retailRevamp.Service;

import com.example.retailRevamp.Model.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IUserService {

    public void saveEntry(UserModel userModel);

    public List<UserModel> getAllEntry();


    public Optional<UserModel> findEntry(int id);

    public boolean deleteEntry(int id);
    public UserModel findUserById(int id);
    public List<UserModel> getUsersWithSentiment();

}
