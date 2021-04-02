package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.socialsignin.spring.data.dynamodb.marshaller.Instant2IsoDynamoDBMarshaller;

import java.time.Instant;

import static com.atstudio.spacedlearningbot.database.config.migrations.DynamoDbModifier.CHANGELOG_TABLE;

@DynamoDBTable(tableName = CHANGELOG_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MigrationEntity {

    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "executedAt")
    @DynamoDBTypeConverted(converter = Instant2IsoDynamoDBMarshaller.class)
    private Instant executedAt;

}
