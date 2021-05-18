package com.atstudio.spacedlearningbot.database.entity.base;

import lombok.NoArgsConstructor;

import java.text.MessageFormat;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EntityIdMapper {

    private static final String DELIMITER = "#";

    public static String withPrefix(EntityType entityType, String plainId) {
        return withDelimiter(entityType) + plainId;
    }

    public static String extractId(String joined, EntityType entityType) {
        String withDelimiter = withDelimiter(entityType);
        if (!joined.startsWith(withDelimiter)) {
            throw new IllegalArgumentException(MessageFormat.format(
                    "Provided string {0} does not start with entity {1} prefix",
                    joined, entityType
            ));
        }
        return joined.replace(withDelimiter, "");
    }

    private static String withDelimiter(EntityType entityType) {
        return entityType.getName() + DELIMITER;
    }

}
