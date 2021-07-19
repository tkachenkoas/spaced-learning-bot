package com.atstudio.spacedlearningbot.telegram.study;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RepetitionIntervalCalculator {

    private final Map<Integer, Integer> LEVEL_TO_INTERVAL_HOURS = new ConcurrentHashMap<>();

    public RepetitionIntervalCalculator() {
        LEVEL_TO_INTERVAL_HOURS.put(0, 4);
        LEVEL_TO_INTERVAL_HOURS.put(1, 8);
    }

    public int repetitionIntervalForExerciseLevel(Integer level) {
        if (level < 1) {
            return LEVEL_TO_INTERVAL_HOURS.get(0);
        }
        Integer cached = LEVEL_TO_INTERVAL_HOURS.get(level);
        if (cached == null) {
            cached = repetitionIntervalForExerciseLevel(level - 1) + repetitionIntervalForExerciseLevel(level - 2);
            LEVEL_TO_INTERVAL_HOURS.put(level, cached);
        }
        return cached;
    }

}
