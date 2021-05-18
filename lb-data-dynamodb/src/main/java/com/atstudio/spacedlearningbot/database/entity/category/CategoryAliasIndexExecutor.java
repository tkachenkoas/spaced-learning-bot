package com.atstudio.spacedlearningbot.database.entity.category;

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
public class CategoryAliasIndexExecutor implements MigrationExecutor {

    @Override
    public String getId() {
        return "category_alias_index";
    }

    @Override
    public void execute(AmazonDynamoDB dynamoDB, IDynamoDBMapper dynamoDBMapper) {
        UpdateTableRequest updateTableRequest = new UpdateTableRequest();
        updateTableRequest.setTableName(PrimaryKey.MAIN_TABLE_NAME);

        updateTableRequest.setAttributeDefinitions(List.of(
                new AttributeDefinition(CategoryEntity.ATTRIBUTE_CATEGORY_ALIAS, "S")
        ));

        GlobalSecondaryIndexUpdate indexCreate = new GlobalSecondaryIndexUpdate()
                .withCreate(new CreateGlobalSecondaryIndexAction()
                        .withIndexName("CategoryByAliasIndex")
                        .withKeySchema(List.of(
                                new KeySchemaElement(PrimaryKey.ATTRIBUTE_OWNER_ID, KeyType.HASH),
                                new KeySchemaElement(CategoryEntity.ATTRIBUTE_CATEGORY_ALIAS, KeyType.RANGE)
                        ))
                        .withProvisionedThroughput(DEFAULT_THROUGHPUT)
                        .withProjection(new Projection().withProjectionType("ALL"))
                );

        updateTableRequest.setGlobalSecondaryIndexUpdates(
                List.of(indexCreate)
        );
        dynamoDB.updateTable(updateTableRequest);
    }
}
