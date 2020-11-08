package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.database.IFlashCardsDao;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.Exercise;
import com.atstudio.spacedlearningbot.domain.ExerciseDirection;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.atstudio.spacedlearningbot.domain.ExerciseDirection.DIRECT;

@Service
@AllArgsConstructor
public class FlashCardService implements IFlashCardService {

    private final IFlashCardsDao flashCardsDao;

    @Override
    public void addFlashCardToCategory(Category category, FlashCard flashCard) {
        FlashCard saved = flashCardsDao.saveFlashCard(category, flashCard);
        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise = createNewExercise(saved, DIRECT);


        if (flashCard.isBiDirectional()) {

        }
    }

    private Exercise createNewExercise(FlashCard saved, ExerciseDirection direction) {
        return new Exercise().withDirection(direction)
                .withFlashCard(saved)
                .withLevel(0);
    }
}
