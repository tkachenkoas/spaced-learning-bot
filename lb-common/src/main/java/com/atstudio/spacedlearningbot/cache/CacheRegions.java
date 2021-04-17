package com.atstudio.spacedlearningbot.cache;

import lombok.Getter;

import java.time.Duration;

import static com.atstudio.spacedlearningbot.cache.CacheRegionStrings.*;
import static java.time.Duration.ofSeconds;

@Getter
enum CacheRegions {
    CURRENT_ACTIVITY(ofSeconds(300), CURRENT_ACTIVITY_CACHE),
    DELETE_CATEGORY_ACTIVITY(ofSeconds(300), DELETE_CATEGORY_ACTIVITY_CACHE),
    CATEGORY(ofSeconds(300), CATEGORY_CACHE);

    private final Duration duration;
    private final String cacheName;

    CacheRegions(Duration duration, String cacheName) {
        this.duration = duration;
        this.cacheName = cacheName;
    }

}
