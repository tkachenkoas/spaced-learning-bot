package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.database.IExerciseDao;
import com.atstudio.spacedlearningbot.database.IFlashCardsDao;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.Exercise;
import com.atstudio.spacedlearningbot.domain.ExerciseDirection;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.atstudio.spacedlearningbot.domain.ExerciseDirection.DIRECT;
import static com.atstudio.spacedlearningbot.domain.ExerciseDirection.REVERSED;

@Service
@AllArgsConstructor
@Transactional
public class FlashCardService implements IFlashCardService {

    private final IFlashCardsDao flashCardsDao;
    private final IExerciseDao exerciseDao;

    @Override
    public void addFlashCardToCategory(Category category, FlashCard flashCard) {
        FlashCard saved = flashCardsDao.saveFlashCard(category, flashCard);
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(
                createNewExercise(saved, DIRECT)
        );
        if (flashCard.isBiDirectional()) {
            exercises.add(
                    createNewExercise(saved, REVERSED)
            );
        }
        exerciseDao.save(exercises, category.getOwnerId());
    }

    @Override
    public List<Exercise> getExercisesAvailableForRepetition(String ownerId) {
        return exerciseDao.loadExercisesAwaitingRepetition(ownerId);
    }

    @Override
    public List<FlashCard> getFlashcards(String ownerId, List<String> flashCardIds) {
        return flashCardsDao.getFlashcardsByIds(ownerId, flashCardIds);
    }

    private Exercise createNewExercise(FlashCard flashCard, ExerciseDirection direction) {
        return new Exercise().withDirection(direction)
                .withFlashCardId(flashCard.getId())
                .withNextRepetition(Instant.now())
                .withLevel(0);
    }
}
