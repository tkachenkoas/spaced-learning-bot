package com.atstudio.spacedlearningbot.telegram.updateprocessors.callback;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallback;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallbackSerializer;
import com.github.tkachenkoas.telegramstarter.RootUpdateHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ActivityCallbackUpdateHandler implements RootUpdateHandler {

    private final List<ActivityCallbackUpdateProcessor> updateProcessorList;

    @Override
    public boolean applicableFor(Update update) {
        return update.getCallbackQuery() != null;
    }

    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        ActivityCallback activityCallback = ActivityCallbackSerializer.parse(callbackQuery.getData());
        for (ActivityCallbackUpdateProcessor updateProcessor : updateProcessorList) {
            if (updateProcessor.applicableFor(activityCallback)) {
                updateProcessor.process(update, activityCallback);
                return;
            }
        }
        log.warn("Wasn't able to find applicable callback update processor for callback {}", callbackQuery);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
