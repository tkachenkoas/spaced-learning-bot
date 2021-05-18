package com.atstudio.spacedlearningbot.database.config.migrations;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.atstudio.spacedlearningbot.database.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;
import org.socialsignin.spring.data.dynamodb.marshaller.Instant2IsoDynamoDBMarshaller;

import java.time.Instant;

import static com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey.MAIN_TABLE_NAME;

@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = MAIN_TABLE_NAME)
@Data
@With
@AllArgsConstructor
public class MigrationEntity extends BaseEntity {

    @DynamoDBAttribute(attributeName = "executedAt")
    @DynamoDBTypeConverted(converter = Instant2IsoDynamoDBMarshaller.class)
    private Instant executedAt;

}
