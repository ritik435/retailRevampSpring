package com.example.retailRevamp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class MongoDBCheckController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public String checkDBConnection() {
        try {
            // Try running a query on a test collection to verify connection
            mongoTemplate.executeCommand("{ ping: 1 }");
            return "✅ MongoDB is connected! : "+mongoTemplate.getDb().getName();
        } catch (Exception e) {
            return "❌ MongoDB connection failed: " + e.getMessage();
        }
    }
}
