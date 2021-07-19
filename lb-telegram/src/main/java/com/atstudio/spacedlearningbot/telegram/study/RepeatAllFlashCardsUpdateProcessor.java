package com.atstudio.spacedlearningbot.telegram.study;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.InitActivityEvent;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.command.DirectCommandUpdateProcessor;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.REPEAT_ALL_FLASHCARDS;

@Component
@AllArgsConstructor
public class RepeatAllFlashCardsUpdateProcessor implements DirectCommandUpdateProcessor {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<String> applicableCommands() {
        return List.of("/repeat_all_flashcards");
    }

    @Override
    public void process(Update update) {
        eventPublisher.publishEvent(
                new InitActivityEvent(REPEAT_ALL_FLASHCARDS, update)
        );
    }
}
