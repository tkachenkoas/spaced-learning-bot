package com.atstudio.spacedlearningbot.telegram.category;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallback;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallbackSerializer;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class CategoryUtils {

    public static InlineKeyboardButton keyboardButtonWithCategoryCallback(
            String buttonText, String categoryId, ActivityType activityType
    ) {
        return new InlineKeyboardButton(buttonText)
                .setCallbackData(
                        categoryActivityCallback(categoryId, activityType)
                );
    }

    public static String categoryActivityCallback(String categoryId, ActivityType activityType) {
        return ActivityCallbackSerializer.serialize(
                new ActivityCallback(activityType, categoryId)
        );
    }

}
