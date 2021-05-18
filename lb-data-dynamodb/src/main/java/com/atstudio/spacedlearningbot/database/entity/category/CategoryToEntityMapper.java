package com.atstudio.spacedlearningbot.database.entity.category;

import com.atstudio.spacedlearningbot.database.entity.base.EntityIdMapper;
import com.atstudio.spacedlearningbot.database.entity.base.EntityType;
import com.atstudio.spacedlearningbot.database.entity.base.IdentifierGenerator;
import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import com.atstudio.spacedlearningbot.domain.Category;

import static java.util.Objects.requireNonNullElseGet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

class CategoryToEntityMapper {

    static CategoryEntity fromCategory(Category category) {
        CategoryEntity result = new CategoryEntity();

        String alias = requireNonNullElseGet(
                category.getAlias(),
                () -> randomAlphanumeric(5)
        );

        result.setAlias(alias);

        String categoryId = requireNonNullElseGet(
                category.getId(), IdentifierGenerator::shortId
        );
        result.setPrimaryKey(
                new PrimaryKey(
                        category.getOwnerId(),
                        EntityIdMapper.withPrefix(EntityType.CATEGORY, categoryId)
                )
        );

        result.setName(category.getName());
        return result;
    }

    static Category toCategory(CategoryEntity entity) {
        return new Category()
                .withAlias(entity.getAlias())
                .withName(entity.getName())
                .withOwnerId(entity.getOwnerId())
                .withId(EntityIdMapper.extractId(
                        entity.getEntityId(), EntityType.CATEGORY
                ));
    }

}
