package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.domain.Exercise;

import java.util.List;

public interface IExerciseDao {

    List<Exercise> save(List<Exercise> exercises, String ownerId);

    List<Exercise> loadExercisesAwaitingRepetition(String ownerId);
}
