package com.atstudio.spacedlearningbot.database.entity;

import com.atstudio.spacedlearningbot.domain.Category;
import lombok.Data;

import java.util.UUID;

import static java.util.Objects.requireNonNullElseGet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Data
public class CategoryEntity {
    private String id;
    private String chatScopedId;
    private String name;
    private Long chatId;

    public static CategoryEntity fromCategory(Category category, Long chatId) {
        CategoryEntity result = new CategoryEntity();

        result.setId(requireNonNullElseGet(
                category.getId(),
                () -> UUID.randomUUID().toString()
        ));
        result.setChatScopedId(requireNonNullElseGet(
                category.getChatScopedId(),
                () -> randomAlphanumeric(5))
        );

        result.setChatId(chatId);
        result.setName(category.getName());
        return result;
    }

    public Category toCategory() {
        return new Category()
                .withId(this.id)
                .withChatScopedId(this.chatScopedId)
                .withName(this.name);
    }
}
