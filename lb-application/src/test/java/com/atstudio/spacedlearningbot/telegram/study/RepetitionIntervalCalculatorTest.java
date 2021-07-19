package com.atstudio.spacedlearningbot.telegram.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RepetitionIntervalCalculatorTest {

    private RepetitionIntervalCalculator underTest;

    @BeforeEach
    void init() {
        underTest = new RepetitionIntervalCalculator();
    }

    Map<Integer, Integer> EXPECTED = Map.of(
            0, 4,
            1, 8,
            2, 12,
            3, 20,
            4, 32,
            5, 52,
            6, 84
    );

    @Test
    void willGetRepetitionInterval() {
        EXPECTED.forEach((level, interval) ->
                assertThat(underTest.repetitionIntervalForExerciseLevel(level))
                        .isEqualTo(interval)
        );
    }

    @Test
    void willCalculateAnyLevelsOnDemand() {
        assertThat(underTest.repetitionIntervalForExerciseLevel(-5))
                .isEqualTo(4);

        assertThat(underTest.repetitionIntervalForExerciseLevel(7))
                .isEqualTo(136);
    }

}