package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import static lombok.AccessLevel.NONE;

@DynamoDBTable(tableName = "FlashCard")
@Getter
@Setter
public class FlashCardEntity {
    @Id
    @Getter(NONE)
    @Setter(NONE)
    private FlashCardEntityPrimaryKey primaryKey;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "CategoryAliasIndex")
    @DynamoDBAttribute(attributeName = "categoryAlias")
    private String categoryAlias;

    @DynamoDBAttribute(attributeName = "biDirectional")
    private boolean biDirectional;

    @DynamoDBAttribute(attributeName = "repetitionMode")
    private Integer repetitionMode;

    @DynamoDBAttribute(attributeName = "left")
    private String left;

    @DynamoDBAttribute(attributeName = "right")
    private String right;

    @DynamoDBHashKey(attributeName = "flashCardId")
    public String getFlashCardId() {
        return primaryKey.getFlashCardId();
    }

    public void setFlashCardId(String flashCardId) {
        this.primaryKey = new FlashCardEntityPrimaryKey(flashCardId);
    }
}
