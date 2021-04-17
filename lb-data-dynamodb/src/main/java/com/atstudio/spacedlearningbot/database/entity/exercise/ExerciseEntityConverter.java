package com.atstudio.spacedlearningbot.database.entity.exercise;

import com.atstudio.spacedlearningbot.database.enummapper.ExerciseDirectionToCodeMapper;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.Exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExerciseEntityConverter {

    static ExerciseEntity toEntity(Exercise exercise, Category category) {
        ExerciseEntity result = new ExerciseEntity();
        result.setCategoryId(category.getId());
        result.setFlashCardId(exercise.getFlashCardId());
        result.setOwnerId(category.getOwnerId());
        result.setLevel(exercise.getLevel());
        result.setDirectionCode(ExerciseDirectionToCodeMapper.getCode(
                exercise.getDirection()
        ));
        result.setNextRepetition(exercise.getNextRepetition());
        return result;
    }

    public static Exercise toExercise(ExerciseEntity entity) {
        Exercise exercise = new Exercise();
        exercise.setFlashCardId(entity.getFlashCardId());
        exercise.setLevel(entity.getLevel());
        exercise.setNextRepetition(entity.getNextRepetition());
        exercise.setId(entity.getId());
        exercise.setDirection(ExerciseDirectionToCodeMapper.getDirection(
                entity.getDirectionCode()
        ));
        return exercise;
    }

    public static List<Exercise> toExerciseList(Iterable<ExerciseEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(ExerciseEntityConverter::toExercise)
                .collect(Collectors.toList());
    }

}
