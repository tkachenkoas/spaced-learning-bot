package com.atstudio.spacedlearningbot.telegram.owner;

import com.atstudio.spacedlearningbot.service.CurrentOwnerIdHolder;
import com.github.tkachenkoas.telegramstarter.UpdatePostHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class OwnerCleaningUpdatePostHandler implements UpdatePostHandler {

    @Override
    public void postHandle(Update update) {
        CurrentOwnerIdHolder.clear();
    }
}
