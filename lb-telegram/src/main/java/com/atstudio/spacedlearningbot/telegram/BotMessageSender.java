package com.atstudio.spacedlearningbot.telegram;

import com.atstudio.spacedlearningbot.utils.TgBotApiObjectsUtils;
import com.atstudio.telegrambot.starterpack.api.TgApiExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@AllArgsConstructor
@Component
public class BotMessageSender {

    private final TgApiExecutor tgApiExecutor;

    public void sendMessage(SendMessage sendMessage) {
        tgApiExecutor.execute(sendMessage);
    }

    public void sendMessage(Long chatId, String message) {
        tgApiExecutor.execute(
                new SendMessage(chatId, message)
        );
    }

    public void sendMessageToSourceChat(Update update, String message) {
        tgApiExecutor.execute(
                new SendMessage(TgBotApiObjectsUtils.getUpdateChatId(update), message)
        );
    }

}
