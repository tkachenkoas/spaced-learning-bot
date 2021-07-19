package com.atstudio.spacedlearningbot.telegram.study;

import com.atstudio.spacedlearningbot.domain.Exercise;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import com.github.tkachenkoas.telegramstarter.TgApiExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.atstudio.spacedlearningbot.telegram.study.StudyUtils.keyboardButtonWithStudyCallback;
import static com.vdurmont.emoji.EmojiParser.parseToUnicode;

@Component
@AllArgsConstructor
public class StudyKeyboardSender {

    private static final String CAKE_EMOJI = parseToUnicode(":cake:");
    private static final String BRAIN_EMOJI = parseToUnicode(":brain:");
    private static final String OPEN_BOOK = parseToUnicode(":open_book:");
    private static final String RACOON_EMOJI = parseToUnicode(":raccoon:");
    private static final String REPEAT_EMOJI = parseToUnicode(":repeat:");

    private final Map<String, Integer> EMOJI_TO_INCREASE = new LinkedHashMap<>();
    {
        EMOJI_TO_INCREASE.put(REPEAT_EMOJI, null);
        EMOJI_TO_INCREASE.put(RACOON_EMOJI, -1);
        EMOJI_TO_INCREASE.put(OPEN_BOOK, 0);
        EMOJI_TO_INCREASE.put(BRAIN_EMOJI, 1);
        EMOJI_TO_INCREASE.put(CAKE_EMOJI, 2);
    }

    private final TgApiExecutor tgApiExecutor;
    private final RepetitionIntervalCalculator intervalCalculator;

    public void sendExerciseMarkup(Exercise currentExercise, FlashCard flashcard) {
    }

    private InlineKeyboardMarkup actionsMarkup(Exercise exercise) {
        String exerciseId = exercise.getId();

        List<InlineKeyboardButton> buttons = EMOJI_TO_INCREASE.entrySet().stream()
                .map(entry -> keyboardButtonWithStudyCallback(
                        entry.getKey(), exerciseId, entry.getValue()
                )).collect(Collectors.toList());

        return new InlineKeyboardMarkup(List.of(buttons));
    }
}
