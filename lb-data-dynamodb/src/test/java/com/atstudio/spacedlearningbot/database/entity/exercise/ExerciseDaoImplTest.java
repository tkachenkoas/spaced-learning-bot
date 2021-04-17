package com.atstudio.spacedlearningbot.database.entity.exercise;

import com.atstudio.spacedlearningbot.commons.CurrentTimeProvider;
import com.atstudio.spacedlearningbot.database.IExerciseDao;
import com.atstudio.spacedlearningbot.database.testconfig.InMemoryDbTestContext;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.Exercise;
import org.assertj.core.data.TemporalUnitWithinOffset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.atstudio.spacedlearningbot.domain.ExerciseDirection.DIRECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = InMemoryDbTestContext.class)
class ExerciseDaoImplTest {

    @Autowired
    private IExerciseDao underTest;

    @Autowired
    private CurrentTimeProvider currentTimeProviderMock;

    @Test
    void willSaveExercises() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(
                new Exercise()
                        .withFlashCardId("flashcard-id")
                        .withDirection(DIRECT)
                        .withLevel(0)
                        .withNextRepetition(Instant.now())
        );

        Category category = new Category().withId("some-category-id");
        List<Exercise> saved = underTest.save(exercises, category);
        assertThat(saved).hasSize(1);
        assertThat(saved.get(0))
                .usingRecursiveComparison()
                .ignoringFields("id", "nextRepetition")
                .isEqualTo(exercises.get(0));

        assertThat(saved.get(0).getNextRepetition())
                .isCloseTo(
                        exercises.get(0).getNextRepetition(),
                        new TemporalUnitWithinOffset(1, ChronoUnit.SECONDS)
                );
    }

    @Test
    void willLoadPendingRepetition() {
        Instant now = Instant.now();

        List<Exercise> exercises = new ArrayList<>();
        exercises.add(
                new Exercise()
                        .withFlashCardId("flashcard-id")
                        .withDirection(DIRECT)
                        .withLevel(0)
                        .withNextRepetition(now)
        );

        Category category = new Category()
                .withOwnerId("owner-id")
                .withId("some-category-id");
        underTest.save(exercises, category);
        when(currentTimeProviderMock.getCurrentTime())
                .thenReturn(now.minusSeconds(5));

        List<Exercise> awaitingRepetition = underTest.loadExercisesAwaitingRepetition("owner-id");
        assertThat(awaitingRepetition).isEmpty();

        when(currentTimeProviderMock.getCurrentTime())
                .thenReturn(now.plusSeconds(5));

        awaitingRepetition = underTest.loadExercisesAwaitingRepetition("owner-id");
        assertThat(awaitingRepetition).hasSize(1);
        assertThat(awaitingRepetition.get(0))
                .usingRecursiveComparison()
                .ignoringFields("id", "nextRepetition")
                .isEqualTo(exercises.get(0));
    }

}