package com.atstudio.spacedlearningbot.database.entity.category;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

@DynamoDBTable(tableName = "Category")
@Data
public class CategoryEntity {

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private String id;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "ChatIdIndex")
    @DynamoDBAttribute(attributeName = "chatId")
    private Long chatId;

    @DynamoDBAttribute(attributeName = "alias")
    private String alias;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
