package com.atstudio.spacedlearningbot.telegram.study;

import com.atstudio.spacedlearningbot.domain.Exercise;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.atstudio.spacedlearningbot.domain.ExerciseDirection.DIRECT;
import static com.atstudio.spacedlearningbot.domain.ExerciseDirection.REVERSED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CurrentStudyTest {

    private List<Exercise> exercises;
    private List<FlashCard> flashCards;

    private CurrentStudy underTest;

    @BeforeEach
    void init() {
        exercises = List.of(
                new Exercise()
                        .withId("ex1")
                        .withDirection(DIRECT)
                        .withFlashCardId("fl1"),
                new Exercise()
                        .withId("ex2")
                        .withDirection(REVERSED)
                        .withFlashCardId("fl1"),
                new Exercise()
                        .withId("ex3")
                        .withDirection(DIRECT)
                        .withFlashCardId("fl2"),
                new Exercise()
                        .withId("ex4")
                        .withDirection(REVERSED)
                        .withFlashCardId("fl2")
        );

        flashCards = List.of(
                new FlashCard().withId("fl1"),
                new FlashCard().withId("fl2")
        );

        underTest = new CurrentStudy(exercises, flashCards);
    }

    @Test
    void willSortByDirectionAndFlashcardId() {
        Exercise current = underTest.currentExercise();
        assertThat(current.getId()).isEqualTo("ex1");

        current = underTest.markSuccessAndGetNext();
        assertThat(current.getId()).isEqualTo("ex3");

        current = underTest.markSuccessAndGetNext();
        assertThat(current.getId()).isEqualTo("ex2");

        current = underTest.markSuccessAndGetNext();
        assertThat(current.getId()).isEqualTo("ex4");
    }

    @Test
    void willThrowOnEmptyQueueAndGetNextOperation() {
        Exercise current = underTest.currentExercise();
        assertThat(current.getId()).isEqualTo("ex1");

        underTest.markSuccessAndGetNext();
        underTest.markSuccessAndGetNext();
        underTest.markSuccessAndGetNext();
        current = underTest.markSuccessAndGetNext();

        assertThat(current).isNull();
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> underTest.markSuccessAndGetNext());

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> underTest.markFailureAndGetNext());
    }


    @Test
    void willReturnToQueueOnFailure() {
        Exercise current = underTest.currentExercise();
        assertThat(current.getId()).isEqualTo("ex1");

        current = underTest.markFailureAndGetNext();
        assertThat(current.getId()).isEqualTo("ex3");

        current = underTest.markSuccessAndGetNext();
        assertThat(current.getId()).isEqualTo("ex2");

        current = underTest.markFailureAndGetNext();
        assertThat(current.getId()).isEqualTo("ex4");

        current = underTest.markSuccessAndGetNext();
        assertThat(current.getId()).isEqualTo("ex1");

        current = underTest.markSuccessAndGetNext();
        assertThat(current.getId()).isEqualTo("ex2");

        current = underTest.markSuccessAndGetNext();
        assertThat(current).isNull();
    }

}