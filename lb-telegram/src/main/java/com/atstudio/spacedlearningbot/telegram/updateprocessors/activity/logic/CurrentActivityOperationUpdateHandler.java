package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.logic;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.CurrentActivityFlowUpdateProcessor;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import com.github.tkachenkoas.telegramstarter.api.RootUpdateHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ByActivityTypeBreaker.breakByActivityType;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;

@Component
public class CurrentActivityOperationUpdateHandler implements RootUpdateHandler {

    private final CurrentActivitySessionManager currentActivitySessionManager;
    private final Map<ActivityType, CurrentActivityFlowUpdateProcessor> currentFlowProcessors;

    public CurrentActivityOperationUpdateHandler(
            CurrentActivitySessionManager currentActivitySessionManager,
            List<CurrentActivityFlowUpdateProcessor> currentFlowProcessors) {
        this.currentActivitySessionManager = currentActivitySessionManager;
        this.currentFlowProcessors = breakByActivityType(currentFlowProcessors);
    }

    @Override
    public boolean applicableFor(Update update) {
        Long chatId = getChatId(update);
        return currentActivitySessionManager.getCurrentActivityForChat(chatId) != null;
    }

    @Override
    public void handle(Update update) {
        Long chatId = getChatId(update);
        CurrentActivity activity = currentActivitySessionManager.getCurrentActivityForChat(chatId);
        CurrentActivityFlowUpdateProcessor flowUpdateProcessor = currentFlowProcessors.get(activity);
        flowUpdateProcessor.processUpdateForCurrentActivity(update, activity);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
