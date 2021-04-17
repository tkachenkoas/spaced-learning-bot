package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.core.Ordered;

public interface MigrationExecutor extends Ordered {

    String getId();

    void execute(AmazonDynamoDB dynamoDB);

}
