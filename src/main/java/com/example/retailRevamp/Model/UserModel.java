package com.example.retailRevamp.Model;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users") // Maps to "users" collection in MongoDB
@Data
public class UserModel {
    @Id
    private int id;

    @Indexed(unique = true)
    @NonNull
    private String name;
//    @NonNull
    private String password;
    @DBRef
    private List<BusinessModel> businessList=new ArrayList<>();
}
