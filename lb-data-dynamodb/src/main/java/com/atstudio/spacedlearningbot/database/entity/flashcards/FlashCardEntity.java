package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;

@DynamoDBTable(tableName = "FlashCard")
@Getter
@Setter
public class FlashCardEntity {
    @DynamoDBHashKey(attributeName = "flashCardId")
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private String flashCardId;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "CategoryIdIndex")
    @DynamoDBAttribute(attributeName = "categoryId")
    private String categoryId;

    @DynamoDBAttribute(attributeName = "biDirectional")
    private boolean biDirectional;

    @DynamoDBAttribute(attributeName = "repetitionMode")
    private Integer repetitionMode;

    @DynamoDBAttribute(attributeName = "left")
    private String left;

    @DynamoDBAttribute(attributeName = "right")
    private String right;

}
