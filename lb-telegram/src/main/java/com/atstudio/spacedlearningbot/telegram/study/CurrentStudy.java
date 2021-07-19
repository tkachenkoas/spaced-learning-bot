package com.atstudio.spacedlearningbot.telegram.study;

import com.atstudio.spacedlearningbot.domain.Exercise;
import com.atstudio.spacedlearningbot.domain.FlashCard;

import java.util.*;
import java.util.stream.Collectors;

import static com.atstudio.spacedlearningbot.util.BotCollectionUtils.convertToMap;

public class CurrentStudy {
    private Map<String, Exercise> exercisesById;
    private Map<String, FlashCard> flashCardsById;
    private Queue<String> exerciseQueue;

    public CurrentStudy(List<Exercise> exercises, List<FlashCard> flashCards) {
        this.exercisesById = convertToMap(exercises, Exercise::getId);
        this.flashCardsById = convertToMap(flashCards, FlashCard::getId);
        this.exerciseQueue = exercises.stream()
                .sorted(Comparator.comparing(ex -> ex.getDirection().name() + ex.getFlashCardId()))
                .map(Exercise::getId)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Exercise currentExercise() {
        String exerciseId = exerciseQueue.peek();
        return exerciseId == null ? null
                : exercisesById.get(exerciseId);
    }

    public Exercise markSuccessAndGetNext() {
        pollTopExerciseId();
        return currentExercise();
    }

    public Exercise markFailureAndGetNext() {
        exerciseQueue.add(pollTopExerciseId());
        return currentExercise();
    }

    public FlashCard getFlashcard(Exercise exercise) {
        return flashCardsById.get(
                exercise.getFlashCardId()
        );
    }

    private String pollTopExerciseId() {
        String top = exerciseQueue.poll();
        if (top == null) {
            throw new IllegalStateException("No more exercises in study");
        }
        return top;
    }
}
