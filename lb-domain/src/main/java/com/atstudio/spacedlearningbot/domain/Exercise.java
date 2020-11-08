package com.atstudio.spacedlearningbot.domain;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private UUID uuid;
    private FlashCard flashCard;
    private ExerciseDirection direction;
    private int level;
    private ZonedDateTime nextRepetition;
}
