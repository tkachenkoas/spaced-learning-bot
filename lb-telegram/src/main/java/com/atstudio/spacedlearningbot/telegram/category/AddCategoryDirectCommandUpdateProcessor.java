package com.atstudio.spacedlearningbot.telegram.category;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.InitActivityEvent;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.command.DirectCommandUpdateProcessor;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.CREATE_CATEGORY;
import static java.util.Arrays.asList;

@Component
@AllArgsConstructor
public class AddCategoryDirectCommandUpdateProcessor implements DirectCommandUpdateProcessor {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<String> applicableCommands() {
        return asList("/add_category");
    }

    @Override
    public void process(Update update) {
        eventPublisher.publishEvent(
                new InitActivityEvent(CREATE_CATEGORY, update)
        );
    }
}
