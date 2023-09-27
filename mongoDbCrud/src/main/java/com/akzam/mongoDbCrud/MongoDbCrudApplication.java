package com.akzam.mongoDbCrud;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongock
@EnableMongoAuditing
@SpringBootApplication
public class MongoDbCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbCrudApplication.class, args);
	}

}
