package com.atstudio.spacedlearningbot.database.entity.exercise;

import com.atstudio.spacedlearningbot.commons.CurrentTimeProvider;
import com.atstudio.spacedlearningbot.database.IExerciseDao;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.atstudio.spacedlearningbot.database.entity.exercise.ExerciseEntityConverter.toExerciseList;
import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class ExerciseDaoImpl implements IExerciseDao {

    private final ExerciseEntityRepository repository;
    private final CurrentTimeProvider currentTimeProvider;

    @Override
    public List<Exercise> save(List<Exercise> exercises, Category category) {
        List<ExerciseEntity> entities = exercises.stream()
                .map(exercise -> ExerciseEntityConverter.toEntity(exercise, category))
                .collect(toList());

        return toExerciseList(repository.saveAll(entities));

    }

    @Override
    public List<Exercise> loadExercisesAwaitingRepetition(String ownerId) {
        List<ExerciseEntity> entities = repository.findAllByOwnerIdAndNextRepetitionBefore(
                ownerId, currentTimeProvider.getCurrentTime()
        );
        return toExerciseList(entities);
    }
}
