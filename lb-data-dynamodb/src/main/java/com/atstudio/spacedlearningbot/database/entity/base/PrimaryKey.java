package com.atstudio.spacedlearningbot.database.entity.base;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryKey {

    public static final String MAIN_TABLE_NAME = "Spaced_learning_bot";
    public static final String ATTRIBUTE_OWNER_ID = "Owner_Id";
    public static final String ATTRIBUTE_ENTITY_ID = "Entity_Id";

    @DynamoDBTyped
    private String ownerId;
    @DynamoDBTyped
    private String entityId;

    @DynamoDBHashKey(attributeName = ATTRIBUTE_OWNER_ID)
    public String getOwnerId() {
        return ownerId;
    }

    @DynamoDBRangeKey(attributeName = ATTRIBUTE_ENTITY_ID)
    public String getEntityId() {
        return entityId;
    }
}
