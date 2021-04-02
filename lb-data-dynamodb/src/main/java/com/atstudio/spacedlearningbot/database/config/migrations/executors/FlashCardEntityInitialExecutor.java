package com.atstudio.spacedlearningbot.database.config.migrations.executors;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.atstudio.spacedlearningbot.database.config.migrations.MigrationExecutor;
import com.atstudio.spacedlearningbot.database.entity.flashcards.FlashCardEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.atstudio.spacedlearningbot.database.config.migrations.DefaultProvisionCapacity.DEFAULT_THROUGHPUT;

@Component
@AllArgsConstructor
@Slf4j
public class FlashCardEntityInitialExecutor implements MigrationExecutor {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public String getId() {
        return "flashcard_entity_initial";
    }

    @Override
    public void execute(AmazonDynamoDB dynamoDB) {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(FlashCardEntity.class);
        createTableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(10L, 1L)
        );
        createTableRequest.getGlobalSecondaryIndexes()
                .forEach(index -> {
                    index.setProjection(new Projection().withProjectionType("ALL"));
                    index.setProvisionedThroughput(DEFAULT_THROUGHPUT);
                });
        dynamoDB.createTable(createTableRequest);
    }
}
