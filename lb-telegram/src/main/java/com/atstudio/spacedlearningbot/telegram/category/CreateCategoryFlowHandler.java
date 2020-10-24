package com.atstudio.spacedlearningbot.telegram.category;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.service.ICategoryService;
import com.atstudio.spacedlearningbot.telegram.messages.BotMessageProvider;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityFinishedEvent;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityInitializer;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.CurrentActivityFlowUpdateProcessor;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import com.github.tkachenkoas.telegramstarter.api.TgApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.CREATE_CATEGORY;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getMessageText;

@Component
@RequiredArgsConstructor
public class CreateCategoryFlowHandler implements CurrentActivityFlowUpdateProcessor, ActivityInitializer {

    private final TgApiExecutor executor;
    private final BotMessageProvider messageProvider;
    private final ICategoryService categoryService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ActivityType applicableForActivityType() {
        return CREATE_CATEGORY;
    }

    @Override
    public void initActivity(Update update) {
        executor.execute(new SendMessage(
                getChatId(update),
                messageProvider.getMessage("enter_category_name")
        ));
    }

    @Override
    public void processUpdateForCurrentActivity(Update update, CurrentActivity currentActivity) {
        // only applicable operation -> set name
        Category category = categoryService.createCategory(
                getChatId(update),
                new Category().withName(
                        getMessageText(update)
                )
        );

        executor.execute(new SendMessage(
                getChatId(update),
                messageProvider.getMessage("category_created", category.getName())
        ));

        eventPublisher.publishEvent(new ActivityFinishedEvent(getChatId(update)));
    }
}
