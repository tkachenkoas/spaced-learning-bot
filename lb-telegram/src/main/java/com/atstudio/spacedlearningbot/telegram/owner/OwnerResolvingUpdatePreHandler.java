package com.atstudio.spacedlearningbot.telegram.owner;

import com.atstudio.spacedlearningbot.service.CurrentOwnerIdHolder;
import com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tkachenkoas.telegramstarter.UpdatePreHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class OwnerResolvingUpdatePreHandler implements UpdatePreHandler {

    private final ObjectMapper LOGGING_MAPPER;

    public OwnerResolvingUpdatePreHandler() {
        this.LOGGING_MAPPER = new ObjectMapper();
        LOGGING_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    @SneakyThrows
    public void preHandle(Update update) {
        log.info("Received update from telegram: {}", LOGGING_MAPPER.writeValueAsString(update));
        CurrentOwnerIdHolder.setCurrentOwnerId(TgBotApiObjectsUtils.getChatId(update).toString());
    }
}
