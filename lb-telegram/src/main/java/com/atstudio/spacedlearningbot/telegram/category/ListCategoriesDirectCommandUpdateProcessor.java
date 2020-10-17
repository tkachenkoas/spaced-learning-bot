package com.atstudio.spacedlearningbot.telegram.category;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.service.ICategoryService;
import com.atstudio.spacedlearningbot.telegram.messages.BotMessageProvider;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.ActivityCallback;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.ActivityCallbackSerializer;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.ActivityType;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.command.DirectCommandUpdateProcessor;
import com.github.tkachenkoas.telegramstarter.api.TgApiExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.ActivityType.ADD_FLASHCARDS;
import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.ActivityType.REPEAT_FLASHCARDS;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;
import static com.vdurmont.emoji.EmojiParser.parseToUnicode;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class ListCategoriesDirectCommandUpdateProcessor implements DirectCommandUpdateProcessor {

    private static final String STUDY_EMOJI = parseToUnicode(":man_student:");
    private static final String ADD_EMOJI = parseToUnicode(":heavy_plus_sign:");

    private final ICategoryService categoryService;
    private final TgApiExecutor executor;
    private final BotMessageProvider messageProvider;

    @Override
    public List<String> applicableCommands() {
        return asList("flashcards_list");
    }

    @Override
    public void process(Update update) {
        Long chatId = getChatId(update);
        List<Category> categories = categoryService.getCategoriesForChat(chatId);
        if (categories.isEmpty()) {
            executor.execute(
                    new SendMessage(chatId, messageProvider.getMessage("no_categories_yet"))
            );
            return;
        }

        String message = messageProvider.getMessage("categories_list_actions", STUDY_EMOJI, ADD_EMOJI);

        executor.execute(
                new SendMessage(chatId, message).setReplyMarkup(
                        toInlineKeyboard(categories)
                )
        );
    }

    private InlineKeyboardMarkup toInlineKeyboard(List<Category> categories) {
        return new InlineKeyboardMarkup(
                categories.stream()
                        .map(this::toKeyboardMarkup)
                        .collect(toList())
        );
    }

    private List<InlineKeyboardButton> toKeyboardMarkup(Category category) {
        return Arrays.asList(
                new InlineKeyboardButton(category.getName() + STUDY_EMOJI)
                        .setCallbackData(
                                categoryActivityCallback(category, REPEAT_FLASHCARDS)
                        ),
                new InlineKeyboardButton(ADD_EMOJI)
                        .setCallbackData(
                                categoryActivityCallback(category, ADD_FLASHCARDS)
                        )
        );
    }

    private String categoryActivityCallback(Category category, ActivityType activityType) {
        return ActivityCallbackSerializer.serialize(
                new ActivityCallback(activityType, category.getChatScopedId())
        );
    }
}
