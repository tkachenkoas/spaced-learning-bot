package com.atstudio.spacedlearningbot.database.entity.exercise;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.atstudio.spacedlearningbot.database.config.migrations.MigrationExecutor;
import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.atstudio.spacedlearningbot.database.config.migrations.DynamoDbTableUtils.DEFAULT_THROUGHPUT;

@Component
@AllArgsConstructor
@Slf4j
public class ExerciseForRepetitionIndexExecutor implements MigrationExecutor {

    public static final String EXERCISES_BY_REPETITION_INDEX = "ExerciseByRepetitionIndex";

    @Override
    public String getId() {
        return "exercise_by_repetition_index";
    }

    @Override
    public void execute(AmazonDynamoDB dynamoDB, IDynamoDBMapper dynamoDBMapper) {
        UpdateTableRequest updateTableRequest = new UpdateTableRequest();
        updateTableRequest.setTableName(PrimaryKey.MAIN_TABLE_NAME);

        updateTableRequest.setAttributeDefinitions(List.of(
                new AttributeDefinition(ExerciseEntity.ATTRIBUTE_NEXT_REPETITION, "S")
        ));

        GlobalSecondaryIndexUpdate byNextRepetitionIndex = new GlobalSecondaryIndexUpdate()
                .withCreate(new CreateGlobalSecondaryIndexAction()
                        .withIndexName(EXERCISES_BY_REPETITION_INDEX)
                        .withKeySchema(List.of(
                                new KeySchemaElement(PrimaryKey.ATTRIBUTE_OWNER_ID, KeyType.HASH),
                                new KeySchemaElement(ExerciseEntity.ATTRIBUTE_NEXT_REPETITION, KeyType.RANGE)
                        ))
                        .withProvisionedThroughput(DEFAULT_THROUGHPUT)
                        .withProjection(new Projection().withProjectionType("ALL"))
                );

        updateTableRequest.setGlobalSecondaryIndexUpdates(
                List.of(byNextRepetitionIndex)
        );
        dynamoDB.updateTable(updateTableRequest);
    }
}
