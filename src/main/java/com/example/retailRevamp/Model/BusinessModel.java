package com.example.retailRevamp.Model;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "business") // Maps to "users" collection in MongoDB
@Data
public class BusinessModel {
    @Id
    private int id;
    @Indexed(unique = true)
    @NonNull
    private String gstName;
    @NonNull
    private String name;
    private Integer turnOver;
}
