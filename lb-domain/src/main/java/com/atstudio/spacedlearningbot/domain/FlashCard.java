package com.atstudio.spacedlearningbot.domain;


import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class FlashCard {
    private String id;
    private ExcerciseType type;
    private boolean biDirectional;
    private String left;
    private String right;
}
