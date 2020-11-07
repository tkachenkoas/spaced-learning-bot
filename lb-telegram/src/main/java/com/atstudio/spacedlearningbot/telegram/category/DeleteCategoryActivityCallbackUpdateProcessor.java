package com.atstudio.spacedlearningbot.telegram.category;

import com.atstudio.spacedlearningbot.service.ICategoryService;
import com.atstudio.spacedlearningbot.telegram.config.CacheRegions;
import com.atstudio.spacedlearningbot.telegram.messages.BotMessageProvider;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallback;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.callback.ActivityCallbackUpdateProcessor;
import com.github.tkachenkoas.telegramstarter.api.TgApiExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.DELETE_CATEGORY;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;

@Slf4j
@Component
@AllArgsConstructor
public class DeleteCategoryActivityCallbackUpdateProcessor implements ActivityCallbackUpdateProcessor {

    private final CacheManager cacheManager;
    private final ICategoryService categoryService;
    private final TgApiExecutor executor;
    private final BotMessageProvider messageProvider;

    @Override
    public boolean applicableFor(ActivityCallback callback) {
        return callback.getActivityType() == DELETE_CATEGORY;
    }

    @Override
    public synchronized void process(Update update, ActivityCallback activityCallback) {
        Cache deleteCategoryCache = cacheManager.getCache(CacheRegions.DELETE_CATEGORY.name());
        String categoryId = activityCallback.getPayload();
        Long chatId = getChatId(update);
        String tapCountKey = chatId + categoryId;
        Integer tapCount = deleteCategoryCache.get(tapCountKey, Integer.class);
        if (tapCount == null) {
            deleteCategoryCache.put(tapCountKey, 1);
            return;
        }
        if (tapCount < 2) {
            deleteCategoryCache.put(tapCountKey, tapCount + 1);
            return;
        }

        categoryService.deleteCategory(chatId, categoryId);
        executor.execute(
                new SendMessage(chatId, messageProvider.getMessage("deleted_category"))
        );
        Message message = update.getCallbackQuery().getMessage();
        executor.execute(
                new DeleteMessage(chatId, message.getMessageId())
        );
    }
}
