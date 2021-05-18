package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.atstudio.spacedlearningbot.database.config.migrations.DynamoDbTableUtils.DEFAULT_THROUGHPUT;
import static com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey.MAIN_TABLE_NAME;

@AllArgsConstructor
@Component
@Slf4j
public class DynamoDbModifier {

    public static final String CHANGELOG_PARTITION = "Changelog_entity";

    private final AmazonDynamoDB dynamoDB;
    private final IDynamoDBMapper dynamoDBMapper;
    private final MigrationEntityRepository migrationEntityRepository;
    private final DbChangeLog changeLog;

    @PostConstruct
    @SneakyThrows
    public void executeMigrations() {
        ensureHavingMainTable();

        Map<String, MigrationEntity> executed = new HashMap<>();
        migrationEntityRepository.findAll().forEach(entity -> executed.put(
                entity.getEntityId(), entity
        ));

        for (MigrationExecutor executor : changeLog.getExecutors()) {
            String executorId = executor.getId();
            if (executed.containsKey(executorId)) {
                log.info("Migration id {} already executed", executorId);
                continue;
            }
            Thread.sleep(500);
            executor.execute(dynamoDB, dynamoDBMapper);

            MigrationEntity entity = new MigrationEntity(Instant.now());
            entity.setPrimaryKey(new PrimaryKey(CHANGELOG_PARTITION, executorId));

            migrationEntityRepository.save(
                    entity
            );
            log.info("Successfully executed migration id={}", executorId);
        }
    }

    private void ensureHavingMainTable() {
        List<String> tableNames = dynamoDB.listTables().getTableNames();
        if (tableNames.contains(MAIN_TABLE_NAME)) {
            return;
        }
        // Table key schema
        List<KeySchemaElement> tableKeySchema = new ArrayList<>();
        tableKeySchema.add(new KeySchemaElement()
                .withAttributeName(PrimaryKey.ATTRIBUTE_OWNER_ID)
                .withKeyType(KeyType.HASH));  //Partition key
        tableKeySchema.add(new KeySchemaElement()
                .withAttributeName(PrimaryKey.ATTRIBUTE_ENTITY_ID)
                .withKeyType(KeyType.RANGE));  //Sort key

        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName(MAIN_TABLE_NAME)
                .withProvisionedThroughput(DEFAULT_THROUGHPUT)
                .withKeySchema(tableKeySchema);

        createTableRequest.setAttributeDefinitions(List.of(
                new AttributeDefinition(PrimaryKey.ATTRIBUTE_OWNER_ID, "S"),
                new AttributeDefinition(PrimaryKey.ATTRIBUTE_ENTITY_ID, "S")
        ));

        dynamoDB.createTable(createTableRequest);
    }

}
