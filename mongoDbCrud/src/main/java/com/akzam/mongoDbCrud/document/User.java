package com.akzam.mongoDbCrud.document;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User {

    @MongoId
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "yearOfBirth")
    private Integer yearOfBirth;

    @Indexed(unique = true)
    @Field(name = "phoneNumber")
    private String phoneNumber;

    @Field(name = "secondPhoneNumber")
    private String secondPhoneNumber;

    @CreatedDate
    @Field(name = "creationDate")
    private LocalDateTime creationDate;

}
