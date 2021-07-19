package com.atstudio.spacedlearningbot.telegram.study;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class StudyUtils {

    public static InlineKeyboardButton keyboardButtonWithStudyCallback(
            String emoji, String categoryId, Integer increase
    ) {
        return new InlineKeyboardButton(emoji)
                .setCallbackData(categoryId + "|" + new SelfCheckResult(increase).serialized());
    }

}
