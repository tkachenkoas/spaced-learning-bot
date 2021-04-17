package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class DynamoDbTableUtils {
    public static final ProvisionedThroughput DEFAULT_THROUGHPUT = new ProvisionedThroughput(10L, 10L);

    public static final Projection ALL_PROJECTION = new Projection().withProjectionType("ALL");
}
