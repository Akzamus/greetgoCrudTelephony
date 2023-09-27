package com.akzam.mongoDbCrud.config.migration;

import com.akzam.mongoDbCrud.document.User;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

@ChangeUnit(
        id = "initDatabase",
        order = "1",
        author = "Akzam",
        systemVersion = "1"
)
@RequiredArgsConstructor
public class InitDatabaseMigration {

    private final MongoTemplate mongoTemplate;
    private final String USER_COLLECTION_NAME = "users";
    private final String PHONE_NUMBER_INDEX = "phoneNumber";

    @Execution
    public void initDatabase() {
        mongoTemplate.createCollection(USER_COLLECTION_NAME);

        Index phoneNumberIndex = new Index()
                .on(PHONE_NUMBER_INDEX, Sort.Direction.ASC)
                .unique();

        IndexOperations indexOps = mongoTemplate.indexOps(User.class);
        indexOps.ensureIndex(phoneNumberIndex);
    }

    @RollbackExecution
    public void rollback() {
        IndexOperations indexOps = mongoTemplate.indexOps(USER_COLLECTION_NAME);
        indexOps.dropIndex(PHONE_NUMBER_INDEX);
        mongoTemplate.dropCollection(USER_COLLECTION_NAME);
    }

}
