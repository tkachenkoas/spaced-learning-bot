package com.atstudio.spacedlearningbot.database.config.migrations;

import com.atstudio.spacedlearningbot.database.entity.category.CategoryAliasIndexExecutor;
import com.atstudio.spacedlearningbot.database.entity.exercise.ExerciseForRepetitionIndexExecutor;
import com.atstudio.spacedlearningbot.database.entity.flashcards.FlashCardsForCategoryIndexExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbChangeLog {

    private final List<? extends MigrationExecutor> EXECUTORS_ORDERED = List.of(
            new CategoryAliasIndexExecutor(),
            new FlashCardsForCategoryIndexExecutor(),
            new ExerciseForRepetitionIndexExecutor()
    );

    public List<? extends MigrationExecutor> getExecutors() {
        return List.copyOf(EXECUTORS_ORDERED);
    }
}
