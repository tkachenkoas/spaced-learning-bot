package com.atstudio.spacedlearningbot.database.entity.base;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class IdentifierGenerator {

    public static String shortId() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    public static String longId() {
        return RandomStringUtils.randomAlphabetic(10);
    }

}
