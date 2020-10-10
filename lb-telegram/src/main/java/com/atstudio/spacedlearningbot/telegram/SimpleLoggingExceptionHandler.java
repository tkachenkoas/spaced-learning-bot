package com.atstudio.spacedlearningbot.telegram;

import com.atstudio.telegrambot.starterpack.api.UpdateProcessingExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Slf4j
@Component
public class SimpleLoggingExceptionHandler implements UpdateProcessingExceptionHandler {

    @Override
    public void handleUpdateProcessingException(Update update, Exception e) {
        if (e instanceof TelegramApiRequestException) {
            log.warn("Telegram api exception: " + ((TelegramApiRequestException) e).getApiResponse());
            return;
        }
        log.warn("Encountered exception when processing update: ", e);
    }
}
