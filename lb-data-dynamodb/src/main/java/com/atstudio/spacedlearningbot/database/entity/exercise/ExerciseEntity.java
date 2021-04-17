package com.atstudio.spacedlearningbot.database.entity.exercise;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;
import org.socialsignin.spring.data.dynamodb.marshaller.Instant2IsoDynamoDBMarshaller;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@DynamoDBTable(tableName = "Exercise")
@Data
public class ExerciseEntity {

    @Id
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private String id;

    @DynamoDBAttribute(attributeName = "flashCardId")
    private String flashCardId;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "category_id_and_next_repetition")
    @DynamoDBAttribute(attributeName = "categoryId")
    private String categoryId;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "owner_id_and_next_repetition")
    @DynamoDBAttribute(attributeName = "ownerId")
    private String ownerId;

    @DynamoDBAttribute(attributeName = "directionCode")
    private Integer directionCode;

    @DynamoDBAttribute(attributeName = "level")
    private Integer level;

    @DynamoDBIndexRangeKey(
            attributeName = "nextRepetition",
            globalSecondaryIndexNames = {
                    "owner_id_and_next_repetition",
                    "category_id_and_next_repetition"
            }
    )
    @DynamoDBAttribute(attributeName = "nextRepetition")
    @DynamoDBTypeConverted(converter = Instant2IsoDynamoDBMarshaller.class)
    private Instant nextRepetition;

}
