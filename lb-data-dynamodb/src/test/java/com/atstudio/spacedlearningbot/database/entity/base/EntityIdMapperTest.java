package com.atstudio.spacedlearningbot.database.entity.base;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.atstudio.spacedlearningbot.database.entity.base.EntityType.CATEGORY;
import static com.atstudio.spacedlearningbot.database.entity.base.EntityType.EXERCISE;

class EntityIdMapperTest {

    @Test
    void willAddPrefix() {
        String withPrefix = EntityIdMapper.withPrefix(CATEGORY, "cat-id");

        Assertions.assertThat(withPrefix).contains("cat-id", "Category");
    }

    @Test
    void willExtractStringPrefix() {
        String withPrefix = EntityIdMapper.withPrefix(CATEGORY, "cat-id");

        String payload = EntityIdMapper.extractId(withPrefix, CATEGORY);
        Assertions.assertThat(payload).isEqualTo("cat-id");
    }

    @Test
    void willThrowForEntityMismatch() {
        String withPrefix = EntityIdMapper.withPrefix(CATEGORY, "cat-id");

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> EntityIdMapper.extractId(withPrefix, EXERCISE));
    }

}