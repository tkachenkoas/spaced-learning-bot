package com.atstudio.spacedlearningbot.telegram.category;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.service.CurrentOwnerIdHolder;
import com.atstudio.spacedlearningbot.service.ICategoryService;
import com.atstudio.spacedlearningbot.telegram.messages.BotMessageProvider;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.command.DirectCommandUpdateProcessor;
import com.github.tkachenkoas.telegramstarter.TgApiExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static com.atstudio.spacedlearningbot.telegram.category.CategoryUtils.keyboardButtonWithCategoryCallback;
import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.LIST_CATEGORY_ACTIONS;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class ListCategoriesDirectCommandUpdateProcessor implements DirectCommandUpdateProcessor {

    private final ICategoryService categoryService;
    private final TgApiExecutor executor;
    private final BotMessageProvider messageProvider;

    @Override
    public List<String> applicableCommands() {
        return asList("/flashcards_list");
    }

    @Override
    public void process(Update update) {
        List<Category> categories = categoryService.getCategories(CurrentOwnerIdHolder.getCurrentOwnerId());
        Long chatId = getChatId(update);
        if (categories.isEmpty()) {
            executor.execute(
                    new SendMessage(chatId, messageProvider.getMessage("no_categories_yet"))
            );
            return;
        }

        String message = messageProvider.getMessage("categories_list_info");

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
        return singletonList(
                keyboardButtonWithCategoryCallback(
                        category.getName(), category.getAlias(), LIST_CATEGORY_ACTIONS
                )
        );
    }
}
