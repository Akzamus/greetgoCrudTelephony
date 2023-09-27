package com.akzam.mongoDbCrud.config.migration;

import com.akzam.mongoDbCrud.document.User;
import com.akzam.mongoDbCrud.repository.UserRepository;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ChangeUnit(
        id = "addTestData",
        order = "2",
        author = "Akzam",
        systemVersion = "1"
)
@RequiredArgsConstructor
public class AddTestDataMigration {

    private final UserRepository userRepository;

    @Execution
    public void addTestData() {
        List<User> users = new ArrayList<>();

        users.add(new User("John Doe", 1990, "+7-(123)-456-78-90", "+7-(987)-654-32-10"));
        users.add(new User("Alice Smith", 1985, "+7-(234)-567-89-01", "+7-(876)-543-21-09"));
        users.add(new User("Bob Johnson", 1978, "+7-(345)-678-90-12", "+7-(765)-432-10-98"));
        users.add(new User("Eve Adams", 1982, "+7-(456)-789-01-23", "+7-(654)-321-09-87"));
        users.add(new User("Charlie Brown", 1995, "+7-(567)-890-12-34", "+7-(543)-210-98-76"));
        users.add(new User("Grace Davis", 1989, "+7-(678)-901-23-45", "+7-(432)-109-87-65"));
        users.add(new User("David Lee", 1970, "+7-(789)-012-34-56", "+7-(321)-098-76-54"));
        users.add(new User("Linda Wilson", 1998, "+7-(890)-123-45-67", "+7-(210)-987-65-43"));
        users.add(new User("Sam Robinson", 1987, "+7-(901)-234-56-78", "+7-(098)-765-43-21"));
        users.add(new User("Emma Johnson", 1980, "+7-(012)-345-67-89", "+7-(876)-543-21-09"));

        userRepository.saveAll(users);
    }

    @RollbackExecution
    public void rollback() {
        userRepository.deleteAll();
    }

}
