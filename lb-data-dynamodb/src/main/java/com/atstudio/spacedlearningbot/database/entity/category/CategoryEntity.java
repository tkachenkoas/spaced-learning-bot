package com.atstudio.spacedlearningbot.database.entity.category;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "Category")
public class CategoryEntity {
    @Id
    private CategoryEntityPrimaryKey primaryKey;

    @DynamoDBAttribute(attributeName = "name")
    @Getter
    @Setter
    private String name;

    @DynamoDBHashKey(attributeName = "chatId")
    public Long getChatId() {
        return primaryKey.getChatId();
    }

    public void setChatId(Long chatId) {
        initPrimaryKey();
        this.primaryKey.setChatId(chatId);
    }

    @DynamoDBRangeKey(attributeName = "alias")
    public String getAlias() {
        return this.primaryKey.getAlias();
    }

    public void setAlias(String alias) {
        initPrimaryKey();
        this.primaryKey.setAlias(alias);
    }

    private void initPrimaryKey() {
        if (primaryKey == null) {
            primaryKey = new CategoryEntityPrimaryKey();
        }
    }

}
