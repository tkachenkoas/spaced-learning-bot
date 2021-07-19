package com.atstudio.spacedlearningbot.commons;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CurrentTimeProviderImpl implements CurrentTimeProvider {
    @Override
    public Instant getCurrentTime() {
        return Instant.now();
    }
}
