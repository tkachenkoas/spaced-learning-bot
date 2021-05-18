package com.atstudio.spacedlearningbot.database.entity.flashcards;

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
public class FlashCardsForCategoryIndexExecutor implements MigrationExecutor {

    static final String FLASHCARDS_FOR_CATEGORY_INDEX = "FlashCardsForCategoryIndex";

    @Override
    public String getId() {
        return "flashcard_for_category_index";
    }

    @Override
    public void execute(AmazonDynamoDB dynamoDB, IDynamoDBMapper dynamoDBMapper) {
        UpdateTableRequest updateTableRequest = new UpdateTableRequest();
        updateTableRequest.setTableName(PrimaryKey.MAIN_TABLE_NAME);

        updateTableRequest.setAttributeDefinitions(List.of(
                new AttributeDefinition(FlashCardEntity.ATTRIBUTE_CATEGORY_ID, "S")
        ));

        GlobalSecondaryIndexUpdate forCategoryIndex = new GlobalSecondaryIndexUpdate()
                .withCreate(new CreateGlobalSecondaryIndexAction()
                        .withIndexName(FLASHCARDS_FOR_CATEGORY_INDEX)
                        .withKeySchema(List.of(
                                new KeySchemaElement(PrimaryKey.ATTRIBUTE_OWNER_ID, KeyType.HASH),
                                new KeySchemaElement(FlashCardEntity.ATTRIBUTE_CATEGORY_ID, KeyType.RANGE)
                        ))
                        .withProvisionedThroughput(DEFAULT_THROUGHPUT)
                        .withProjection(new Projection().withProjectionType("ALL"))
                );

        updateTableRequest.setGlobalSecondaryIndexUpdates(
                List.of(forCategoryIndex)
        );
        dynamoDB.updateTable(updateTableRequest);
    }
}
