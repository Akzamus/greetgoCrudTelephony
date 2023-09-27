package com.akzam.mongoDbCrud.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @MongoId
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "yearOfBirth")
    private Integer yearOfBirth;

    @Field(name = "phoneNumber")
    private String phoneNumber;

    @Field(name = "secondPhoneNumber")
    private String secondPhoneNumber;

    @CreatedDate
    @Field(name = "creationDate")
    private LocalDateTime creationDate;

    public User(String name, Integer yearOfBirth, String phoneNumber, String secondPhoneNumber) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
    }

}
