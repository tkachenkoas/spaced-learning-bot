package com.atstudio.spacedlearningbot.database.entity.category;

import com.atstudio.spacedlearningbot.domain.Category;

import static java.util.Objects.requireNonNullElseGet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

class CategoryToEntityMapper {

    static CategoryEntity fromCategory(Category category, Long chatId) {
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

    static Category toCategory(CategoryEntity entity) {
        return new Category()
                .withAlias(entity.getAlias())
                .withName(entity.getName());
    }

}
