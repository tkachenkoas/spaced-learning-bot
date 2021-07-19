package com.atstudio.spacedlearningbot.telegram.study;

import com.atstudio.spacedlearningbot.cache.CacheRegionStrings;
import com.atstudio.spacedlearningbot.domain.Exercise;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import com.atstudio.spacedlearningbot.service.CurrentOwnerIdHolder;
import com.atstudio.spacedlearningbot.service.IFlashCardService;
import com.atstudio.spacedlearningbot.telegram.messages.BotMessageProvider;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityInitializer;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.CurrentActivityFlowUpdateProcessor;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import com.github.tkachenkoas.telegramstarter.TgApiExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.cache.CacheManager;
import java.util.List;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType.REPEAT_ALL_FLASHCARDS;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;
import static com.atstudio.spacedlearningbot.util.BotCollectionUtils.convertList;

@Component
@AllArgsConstructor
public class StudyActivityFlowHandler implements CurrentActivityFlowUpdateProcessor, ActivityInitializer {

    private final IFlashCardService flashCardService;
    private final CacheManager cacheManager;
    private final TgApiExecutor tgApiExecutor;
    private final BotMessageProvider messageProvider;
    private final StudyKeyboardSender studyKeyboardSender;

    @Override
    public ActivityType applicableForActivityType() {
        return REPEAT_ALL_FLASHCARDS;
    }

    @Override
    public void initActivity(Update update, CurrentActivity currentActivity) {
        String ownerId = CurrentOwnerIdHolder.getCurrentOwnerId();

        List<Exercise> forRepetition = flashCardService.getExercisesAvailableForRepetition(ownerId);
        List<FlashCard> flashcards = flashCardService.getFlashcards(ownerId, convertList(forRepetition, Exercise::getId));

        CurrentStudy study = new CurrentStudy(forRepetition, flashcards);
        cacheManager.getCache(CacheRegionStrings.CURRENT_STUDY_CACHE)
                .put(ownerId, study);

        Exercise currentExercise = study.currentExercise();
        FlashCard flashcard = study.getFlashcard(currentExercise);

        tgApiExecutor.execute(new SendMessage(
                getChatId(update),
                messageProvider.getMessage("study_started", forRepetition.size())
        ));

        studyKeyboardSender.sendExerciseMarkup(currentExercise, flashcard);
    }

    @Override
    public void processUpdateForCurrentActivity(Update update, CurrentActivity currentActivity) {

    }

}
