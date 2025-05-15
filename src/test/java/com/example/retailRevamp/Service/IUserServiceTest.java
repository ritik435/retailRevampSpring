package com.example.retailRevamp.Service;

import com.example.retailRevamp.Model.UserModel;
import com.example.retailRevamp.Repository.UserRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IUserServiceTest {

    @Autowired
    public UserRepositories userRepositories;

    @Test
    public void testUserById1(){
//        assertEquals(6, 2+3, "Addition should return correct sum");
        assertNotNull(userRepositories.findUserById(5));
//        assert(userRepositories.findUserById(5));
    }
    @Test
    public void testUserById() {
        UserModel user = userRepositories.findUserById(5);
        assertNotNull(user);
        System.out.println("User details: " + user.getName());
    }

    @Test
    void testAddition() {
//        int result = add(2, 3);
        assertEquals(5, 2+3, "Addition should return correct sum");
    }
}
