package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

public interface MigrationExecutor {

    String getId();

    void execute(AmazonDynamoDB dynamoDB);

}
