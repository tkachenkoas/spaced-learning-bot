package com.atstudio.spacedlearningbot.telegram.utils;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class TgBotApiObjectsUtils {

    public static String getMessageText(Update update) {
        return ofNullable(update.getMessage())
                .map(Message::getText)
                .orElse(null);
    }

    public static Long getChatId(Update update) {
        Optional<Long> fromMessage = ofNullable(update.getMessage())
                .map(Message::getChatId);
        if (fromMessage.isPresent()) {
            return fromMessage.get();
        }
        return ofNullable(update.getCallbackQuery())
                .map(CallbackQuery::getMessage)
                .map(Message::getChatId)
                .orElseThrow();
    }

}
