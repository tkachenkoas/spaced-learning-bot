package com.atstudio.spacedlearningbot.telegram.category;

import com.atstudio.spacedlearningbot.telegram.messages.BotMessageProvider;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallback;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.callback.ActivityCallbackUpdateProcessor;
import com.github.tkachenkoas.telegramstarter.TgApiExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.atstudio.spacedlearningbot.telegram.category.CategoryUtils.keyboardButtonWithCategoryCallback;
import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.*;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;
import static com.vdurmont.emoji.EmojiParser.parseToUnicode;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Slf4j
@Component
@AllArgsConstructor
public class CategoryActionsActivityCallbackUpdateProcessor implements ActivityCallbackUpdateProcessor {

    private static final String STUDY_EMOJI = parseToUnicode(":man_student:");
    private static final String ADD_EMOJI = parseToUnicode(":heavy_plus_sign:");
    private static final String DELETE_EMOJI = parseToUnicode(":x:");

    private final TgApiExecutor executor;
    private final BotMessageProvider messageProvider;

    @Override
    public boolean applicableFor(ActivityCallback callback) {
        return callback.getActivityType().equals(LIST_CATEGORY_ACTIONS);
    }

    @Override
    public void process(Update update, ActivityCallback activityCallback) {
        String categoryId = activityCallback.getPayload();
        executor.execute(
                new SendMessage(getChatId(update), messageProvider.getMessage("select_category_action")
                ).setReplyMarkup(
                        actionsMarkup(categoryId)
                )
        );
    }

    private InlineKeyboardMarkup actionsMarkup(String categoryId) {
        return new InlineKeyboardMarkup(asList(
                /*singletonList(keyboardButtonWithCategoryCallback(
                        messageProvider.getMessage("study_category") + " " + STUDY_EMOJI,
                        categoryId, REPEAT_FLASHCARDS
                )),*/
                singletonList(keyboardButtonWithCategoryCallback(
                        messageProvider.getMessage("add_flashcards") + " " + ADD_EMOJI,
                        categoryId, ADD_FLASHCARDS
                )),
                singletonList(keyboardButtonWithCategoryCallback(
                        messageProvider.getMessage("delete_category") + " " + DELETE_EMOJI,
                        categoryId, DELETE_CATEGORY
                ))
        ));
    }
}
