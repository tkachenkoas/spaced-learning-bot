package com.atstudio.spacedlearningbot.database.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

@EnableDynamoDBRepositories(basePackages = "com.atstudio.spacedlearningbot.database")
@Configuration
public class EnableRepositoriesConfig {
}
