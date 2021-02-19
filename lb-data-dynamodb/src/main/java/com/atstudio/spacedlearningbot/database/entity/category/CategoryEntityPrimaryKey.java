package com.atstudio.spacedlearningbot.database.entity.category;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntityPrimaryKey {
    @DynamoDBHashKey(attributeName = "chatId")
    private Long chatId;
    @DynamoDBRangeKey(attributeName = "alias")
    private String alias;
}
