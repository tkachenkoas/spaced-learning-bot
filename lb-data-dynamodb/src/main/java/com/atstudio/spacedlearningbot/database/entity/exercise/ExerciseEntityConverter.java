package com.atstudio.spacedlearningbot.database.entity.exercise;

import com.atstudio.spacedlearningbot.database.entity.base.EntityIdMapper;
import com.atstudio.spacedlearningbot.database.entity.base.EntityType;
import com.atstudio.spacedlearningbot.database.entity.base.IdentifierGenerator;
import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import com.atstudio.spacedlearningbot.database.enummapper.ExerciseDirectionToCodeMapper;
import com.atstudio.spacedlearningbot.domain.Exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.requireNonNullElseGet;

public class ExerciseEntityConverter {

    static ExerciseEntity toEntity(Exercise exercise, String ownerId) {
        ExerciseEntity result = new ExerciseEntity();

        String exerciseId = requireNonNullElseGet(
                exercise.getId(), IdentifierGenerator::longId
        );
        result.setPrimaryKey(
                new PrimaryKey(
                        ownerId,
                        EntityIdMapper.withPrefix(EntityType.EXERCISE, exerciseId)
                )
        );

        result.setFlashCardId(exercise.getFlashCardId());
        result.setLevel(exercise.getLevel());
        result.setDirectionCode(ExerciseDirectionToCodeMapper.getCode(
                exercise.getDirection()
        ));
        result.setNextRepetition(exercise.getNextRepetition());
        return result;
    }

    public static Exercise toExercise(ExerciseEntity entity) {
        Exercise exercise = new Exercise();
        exercise.setId(EntityIdMapper.extractId(
                entity.getEntityId(), EntityType.EXERCISE
        ));
        exercise.setFlashCardId(entity.getFlashCardId());
        exercise.setLevel(entity.getLevel());
        exercise.setNextRepetition(entity.getNextRepetition());
        exercise.setId(entity.getEntityId());
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
