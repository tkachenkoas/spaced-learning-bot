package com.atstudio.spacedlearningbot.database.entity.category;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

@DynamoDBTable(tableName = "Category")
@Data
public class CategoryEntity {

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private String id;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "OwnerIdIndex")
    @DynamoDBAttribute(attributeName = "ownerId")
    private String ownerId;

    @DynamoDBAttribute(attributeName = "alias")
    private String alias;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

}
