package com.akzam.postgreSqlCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PostgreSqlCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgreSqlCrudApplication.class, args);
    }

}
