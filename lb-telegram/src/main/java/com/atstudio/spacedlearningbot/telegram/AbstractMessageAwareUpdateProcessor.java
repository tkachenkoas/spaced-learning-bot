package com.atstudio.spacedlearningbot.telegram;

import com.atstudio.spacedlearningbot.service.BotMessageProvider;
import com.atstudio.telegrambot.starterpack.api.UpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public abstract class AbstractMessageAwareUpdateProcessor implements UpdateProcessor {

    @Autowired
    private BotMessageProvider botMessageProvider;
    @Autowired
    private BotMessageSender messageSender;

    protected void sendMessage(Long chatId, String messageText) {
        messageSender.sendMessage(chatId, messageText);
    }

    protected void sendMessage(SendMessage sendMessage) {
        messageSender.sendMessage(sendMessage);
    }

    protected void sendMessageToSourceChat(Update update, String messageText) {
        messageSender.sendMessageToSourceChat(update, messageText);
    }

    protected String provideMessageText(String code, String... params) {
        return botMessageProvider.getMessage(code, params);
    }

}
