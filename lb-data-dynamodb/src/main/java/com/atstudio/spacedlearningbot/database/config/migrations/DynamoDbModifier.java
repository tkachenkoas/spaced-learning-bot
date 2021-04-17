package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.atstudio.spacedlearningbot.database.config.migrations.DynamoDbTableUtils.DEFAULT_THROUGHPUT;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toSet;

@AllArgsConstructor
@Component
@Slf4j
public class DynamoDbModifier {

    public static final String CHANGELOG_TABLE = "Changelog";

    private final AmazonDynamoDB dynamoDB;
    private final DynamoDBMapper dynamoDBMapper;
    private final MigrationEntityRepository migrationEntityRepository;
    private final List<MigrationExecutor> executors;

    @PostConstruct
    public void executeMigrations() {
        ensureHavingChangeLog();
        ensureOrdering();

        Map<String, MigrationEntity> executed = new HashMap<>();
        migrationEntityRepository.findAll().forEach(entity -> executed.put(
                entity.getId(), entity
        ));

        for (MigrationExecutor executor : executors) {
            String executorId = executor.getId();
            if (executed.containsKey(executorId)) {
                log.info("Migration id {} already executed", executorId);
                continue;
            }
            executor.execute(dynamoDB);
            migrationEntityRepository.save(
                    new MigrationEntity(executorId, Instant.now())
            );
            log.info("Successfully executed migration id={}", executorId);
        }
    }

    private void ensureOrdering() {
        int uniqueOrdersCount = executors.stream().map(Ordered::getOrder).collect(toSet()).size();
        if (uniqueOrdersCount != executors.size()) {
            throw new IllegalStateException("Orders of migration executors should be unique");
        }
        executors.sort(comparing(Ordered::getOrder));
    }

    private void ensureHavingChangeLog() {
        List<String> tableNames = dynamoDB.listTables().getTableNames();
        if (tableNames.contains(CHANGELOG_TABLE)) {
            return;
        }
        CreateTableRequest createChangeLog = dynamoDBMapper.generateCreateTableRequest(
                MigrationEntity.class
        );
        createChangeLog.setProvisionedThroughput(DEFAULT_THROUGHPUT);
        dynamoDB.createTable(createChangeLog);
    }

}
