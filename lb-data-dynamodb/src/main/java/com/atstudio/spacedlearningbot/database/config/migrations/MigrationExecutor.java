package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;

public interface MigrationExecutor {

    String getId();

    void execute(AmazonDynamoDB dynamoDB, IDynamoDBMapper dynamoDBMapper);

}
