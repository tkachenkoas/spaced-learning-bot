package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity;

import java.util.Arrays;

public enum ActivityType {
    CREATE_CATEGORY(0),
    REPEAT_FLASHCARDS(1),
    ADD_FLASHCARDS(2);

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
