package com.atstudio.spacedlearningbot.service;

public interface BotMessageProvider {

    String getMessage(String code, Object... args);

}
