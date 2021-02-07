package com.atstudio.spacedlearningbot.database.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.atstudio.spacedlearningbot.domain.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import static java.util.Objects.requireNonNullElseGet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

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

    public static CategoryEntity fromCategory(Category category, Long chatId) {
        CategoryEntity result = new CategoryEntity();

        String alias = requireNonNullElseGet(
                category.getAlias(),
                () -> randomAlphanumeric(5)
        );

        result.setAlias(alias);
        result.setChatId(chatId);
        result.setName(category.getName());
        return result;
    }

    public Category toCategory() {
        return new Category()
                .withAlias(this.getAlias())
                .withName(this.name);
    }
}
