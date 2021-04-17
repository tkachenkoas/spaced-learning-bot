package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.Exercise;

import java.util.List;

public interface IExerciseDao {

    List<Exercise> save(List<Exercise> exercises, Category category);

    List<Exercise> loadExercisesAwaitingRepetition(String ownerId);
}
