package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class DefaultProvisionCapacity {
    public static final ProvisionedThroughput DEFAULT_THROUGHPUT = new ProvisionedThroughput(10L, 10L);
}
