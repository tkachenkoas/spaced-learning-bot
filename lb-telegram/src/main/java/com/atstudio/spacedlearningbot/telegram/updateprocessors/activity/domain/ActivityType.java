package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain;

import java.util.Arrays;

public enum ActivityType {
    CREATE_CATEGORY(0),
    LIST_CATEGORY_ACTIONS(1),
    REPEAT_ALL_FLASHCARDS(2),
    ADD_FLASHCARDS(3),
    DELETE_CATEGORY(4);

    private Integer code;

    ActivityType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static ActivityType forCode(Integer code) {
        return Arrays.stream(values())
                .filter(type -> type.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No activity type for code " + code));
    }
}
