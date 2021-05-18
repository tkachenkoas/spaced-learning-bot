package com.atstudio.spacedlearningbot.database.entity.category;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.atstudio.spacedlearningbot.database.entity.base.BaseEntity;
import lombok.Data;

@Data
public class CategoryEntity extends BaseEntity {

    static final String ATTRIBUTE_CATEGORY_ALIAS = "Category_Alias";
    static final String ATTRIBUTE_CATEGORY_NAME = "Category_Name";

    @DynamoDBAttribute(attributeName = ATTRIBUTE_CATEGORY_ALIAS)
    private String alias;

    @DynamoDBAttribute(attributeName = ATTRIBUTE_CATEGORY_NAME)
    private String name;

}
