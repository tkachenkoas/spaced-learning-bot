package com.atstudio.spacedlearningbot.database.entity.base;

import lombok.Getter;

@Getter
public enum EntityType {
    CATEGORY("Category"),
    FLASHCARD("Flashcard"),
    EXERCISE("Exercise");

    private final String name;

    EntityType(String name) {
        this.name = name;
    }
}
