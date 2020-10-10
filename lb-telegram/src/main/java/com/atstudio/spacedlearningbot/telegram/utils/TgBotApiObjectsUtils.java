package com.atstudio.spacedlearningbot.telegram.utils;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static java.util.Optional.ofNullable;

public class TgBotApiObjectsUtils {

    public static String geUpdateMessage(Update update) {
        return ofNullable(update.getMessage())
                .map(Message::getText)
                .orElse(null);
    }

    public static Long getUpdateChatId(Update update) {
        return ofNullable(update.getMessage())
                .map(Message::getChatId)
                .orElse(null);
    }

}
