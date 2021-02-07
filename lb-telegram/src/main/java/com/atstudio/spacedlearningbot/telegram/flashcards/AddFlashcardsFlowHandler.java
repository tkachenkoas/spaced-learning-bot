package com.atstudio.spacedlearningbot.telegram.flashcards;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import com.atstudio.spacedlearningbot.domain.RepetitionMode;
import com.atstudio.spacedlearningbot.service.ICategoryService;
import com.atstudio.spacedlearningbot.service.IFlashCardService;
import com.atstudio.spacedlearningbot.telegram.messages.BotMessageProvider;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityInitializer;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityUpdatedEvent;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.CurrentActivityFlowUpdateProcessor;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallback;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallbackSerializer;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import com.github.tkachenkoas.telegramstarter.api.TgApiExecutor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.ADD_FLASHCARDS;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getMessageText;

@Component
@RequiredArgsConstructor
public class AddFlashcardsFlowHandler implements CurrentActivityFlowUpdateProcessor, ActivityInitializer {

    private static final String CATEGORY_ALIAS = "CATEGORY_ALIAS";

    private final TgApiExecutor executor;
    private final BotMessageProvider messageProvider;
    private final ICategoryService categoryService;
    private final IFlashCardService flashCardService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ActivityType applicableForActivityType() {
        return ADD_FLASHCARDS;
    }

    @Override
    public void initActivity(Update update, CurrentActivity currentActivity) {
        setCategoryForCurrentActivity(update, currentActivity);
        executor.execute(new SendMessage(
                getChatId(update),
                messageProvider.getMessage("enter_flashcards")
        ));
    }

    private void setCategoryForCurrentActivity(Update update, CurrentActivity currentActivity) {
        ActivityCallback activityCallback = ActivityCallbackSerializer.parse(
                update.getCallbackQuery().getData()
        );
        String categoryId = activityCallback.getPayload();
        currentActivity.getDetails().put(CATEGORY_ALIAS, categoryId);
        eventPublisher.publishEvent(
                new ActivityUpdatedEvent(getChatId(update), currentActivity)
        );
    }

    @Override
    public void processUpdateForCurrentActivity(Update update, CurrentActivity currentActivity) {
        FlashCard flashCard = mapToFlashCard(update);
        if (flashCard == null) {
            return;
        }

        Long chatId = getChatId(update);
        Category category = categoryService.getCategoryByAlias(
                chatId, (String) currentActivity.getDetails().get(CATEGORY_ALIAS)
        );

        flashCardService.addFlashCardToCategory(category, flashCard);

        executor.execute(new SendMessage(
                chatId,
                messageProvider.getMessage("flashcard_added", category.getName())
        ));
    }

    private FlashCard mapToFlashCard(Update update) {
        String messageText = getMessageText(update);
        String[] parts = messageText.split("->|<->");
        if (parts.length != 2 || StringUtils.isAnyBlank(parts)) {
            sendWrongCardFormatMessage(getChatId(update));
            return null;
        }
        return new FlashCard().withRepetitionMode(RepetitionMode.SELF_CHECK)
                .withBiDirectional(messageText.contains("<->"))
                .withLeft(parts[0].trim())
                .withRight(parts[1].trim());
    }

    private void sendWrongCardFormatMessage(Long chatId) {
        executor.execute(new SendMessage(
                chatId,
                messageProvider.getMessage("wrong_flashcard_format")
        ));
    }
}
