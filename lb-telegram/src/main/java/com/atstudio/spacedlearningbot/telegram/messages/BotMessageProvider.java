package com.atstudio.spacedlearningbot.telegram.messages;

public interface BotMessageProvider {

    String getMessage(String code, Object... args);

}
