package com.atstudio.spacedlearningbot.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@AllArgsConstructor
@Component
public class BotMessageProviderImpl implements BotMessageProvider {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.ENGLISH);
    }
}
