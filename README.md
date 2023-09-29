# Telephony CRUD with MongoDB and PostgreSQL

## Description

This project is an example of a simple CRUD (Create, Read, Update, Delete) web application using two
different databases: MongoDB and PostgreSQL. Each database works with a separate gradle module of the project,
and a test set of queries in the Postman collection is also available.

## Project launch

To run the project, run the following command in the root of the project:

```bash
docker-compose up
```

## Technologies

Module 1 (MongoDB)

Spring Boot: A framework for creating web applications.
Spring Boot Data MongoDB: For working with the MongoDB database.
Spring Boot Validation: For data validation.
Lombok: To simplify the code.
MapStruct: For mapping between entities.
TestNG: For unit testing.
MongoDB: For data storage.

Module 2 (PostgreSQL)

Spring Boot: A framework for creating web applications.
Spring Boot Data JPA: To work with the database and manage entities.
Spring Boot Validation: For data validation.
Flyway: For PostgreSQL database migrations.
PostgreSQL: For data storage.
Lombok: To simplify the code.
MapStruct: For mapping between entities.
TestNG: For unit testing.

## Using the Postman query collection

For convenient testing of the functionality of your web application, a collection of Postman queries is available, which is located
in the "postman" folder and exported in the "Collection v2.1" format.


**Note:** Make sure that your web application is running and available at the appropriate address and port before
executing queries from the Postman collection.

## .env file

In the root of this project you will find a file named `.env'. This file contains the environment variables used to configure and run the project.

**Important note:**

Please **do not add the `.env` file to your Git repository**. This file usually contains confidential information. In my case, it was added for the convenience of launching and does not carry valuable information.

## About the problem with Mongock

Previously, the project used migrations using Mongock for MongoDB, but they were removed due to an error
java.lang.ClassNotFoundException: io.mongock.runner.springboot.base.MongockApplicationRunner, which occurred only
when the jar file was started. You can view the implementation of migrations with this technology several commits earlier.