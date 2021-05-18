package com.atstudio.spacedlearningbot.database.entity.base;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import static com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey.MAIN_TABLE_NAME;

@DynamoDBTable(tableName = MAIN_TABLE_NAME)
@NoArgsConstructor
public class BaseEntity {

    @Id
    private PrimaryKey primaryKey;

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    @DynamoDBHashKey(attributeName = PrimaryKey.ATTRIBUTE_OWNER_ID)
    public String getOwnerId() {
        return primaryKey.getOwnerId();
    }

    public void setOwnerId(String ownerId) {
        if (this.primaryKey == null) {
            this.primaryKey = new PrimaryKey();
        }
        primaryKey.setOwnerId(ownerId);
    }

    @DynamoDBRangeKey(attributeName = PrimaryKey.ATTRIBUTE_ENTITY_ID)
    public String getEntityId() {
        return primaryKey.getEntityId();
    }

    public void setEntityId(String entityId) {
        if (this.primaryKey == null) {
            this.primaryKey = new PrimaryKey();
        }
        primaryKey.setEntityId(entityId);
    }

}
