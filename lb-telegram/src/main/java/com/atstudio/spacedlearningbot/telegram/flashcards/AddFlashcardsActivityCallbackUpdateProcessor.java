package com.atstudio.spacedlearningbot.telegram.flashcards;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.InitActivityEvent;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallback;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.callback.ActivityCallbackUpdateProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.ADD_FLASHCARDS;

@Component
@RequiredArgsConstructor
public class AddFlashcardsActivityCallbackUpdateProcessor implements ActivityCallbackUpdateProcessor {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public boolean applicableFor(ActivityCallback callback) {
        return callback.getActivityType().equals(ADD_FLASHCARDS);
    }

    @Override
    public void process(Update update, ActivityCallback activityCallback) {
        eventPublisher.publishEvent(
                new InitActivityEvent(ADD_FLASHCARDS, update)
        );
    }
}
