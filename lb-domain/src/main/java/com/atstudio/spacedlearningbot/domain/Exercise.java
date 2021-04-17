package com.atstudio.spacedlearningbot.domain;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private String id;
    private String flashCardId;
    private ExerciseDirection direction;
    private int level;
    private Instant nextRepetition;
}
