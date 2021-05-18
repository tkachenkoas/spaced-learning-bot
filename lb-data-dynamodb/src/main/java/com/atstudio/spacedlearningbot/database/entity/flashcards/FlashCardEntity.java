package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.atstudio.spacedlearningbot.database.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FlashCardEntity extends BaseEntity {

    static final String ATTRIBUTE_CATEGORY_ID = "Category_Id";
    static final String ATTRIBUTE_BIDIRECTIONAL = "Card_Bidirectional";
    static final String ATTRIBUTE_TYPE = "Card_Type";
    static final String ATTRIBUTE_LEFT = "Card_Left";
    static final String ATTRIBUTE_RIGHT = "Card_Right";

    @DynamoDBAttribute(attributeName = ATTRIBUTE_CATEGORY_ID)
    private String categoryId;

    @DynamoDBAttribute(attributeName = ATTRIBUTE_BIDIRECTIONAL)
    private boolean biDirectional;

    @DynamoDBAttribute(attributeName = ATTRIBUTE_TYPE)
    private Integer type;

    @DynamoDBAttribute(attributeName = ATTRIBUTE_LEFT)
    private String left;

    @DynamoDBAttribute(attributeName = ATTRIBUTE_RIGHT)
    private String right;

}
