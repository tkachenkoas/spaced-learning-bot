package com.atstudio.spacedlearningbot.database.entity.config;

import com.atstudio.spacedlearningbot.commons.CurrentTimeProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestBeansConfig {

    @Bean
    public CurrentTimeProvider currentTimeProviderMock() {
        return mock(CurrentTimeProvider.class);
    }
}
