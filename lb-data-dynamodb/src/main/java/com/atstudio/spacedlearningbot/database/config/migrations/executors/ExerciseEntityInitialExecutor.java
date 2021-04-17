package com.atstudio.spacedlearningbot.database.config.migrations.executors;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.atstudio.spacedlearningbot.database.config.migrations.MigrationExecutor;
import com.atstudio.spacedlearningbot.database.entity.exercise.ExerciseEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.atstudio.spacedlearningbot.database.config.migrations.DynamoDbTableUtils.ALL_PROJECTION;
import static com.atstudio.spacedlearningbot.database.config.migrations.DynamoDbTableUtils.DEFAULT_THROUGHPUT;

@Component
@AllArgsConstructor
@Slf4j
public class ExerciseEntityInitialExecutor implements MigrationExecutor {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public String getId() {
        return "exercise_entity_initial";
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void execute(AmazonDynamoDB dynamoDB) {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(ExerciseEntity.class);
        createTableRequest.setProvisionedThroughput(DEFAULT_THROUGHPUT);
        createTableRequest.getGlobalSecondaryIndexes()
                .forEach(index -> {
                    index.setProjection(ALL_PROJECTION);
                    index.setProvisionedThroughput(DEFAULT_THROUGHPUT);
                });
        dynamoDB.createTable(createTableRequest);
    }
}
