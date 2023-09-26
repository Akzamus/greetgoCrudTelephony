package com.akzam.mongoDbCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MongoDbCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbCrudApplication.class, args);
	}

}
