package com.atstudio.spacedlearningbot.database.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
class DynamoDbDevConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;
    @Value("${amazon.dynamodb.region}")
    private String amazonDynamoDBRegion;

    @Bean(destroyMethod = "shutdown")
    public AmazonDynamoDB amazonDynamoDB() {
        var auth =  new BasicAWSCredentials("fakeKey", "fakeSecret");
        return AmazonDynamoDBClientBuilder
                .standard().withCredentials(new AWSStaticCredentialsProvider(auth))
                .withEndpointConfiguration(
                        new AwsClientBuilder
                                .EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
                .build();
    }

    @Bean
    public DynamoDB dynamoDB(AmazonDynamoDB client) {
        return new DynamoDB(client);
    }
}
