package com.atstudio.spacedlearningbot.domain;


import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class FlashCard {
    private RepetitionMode repetitionMode;
    private String chatScopedId;
    private boolean biDirectional;
    private String left;
    private String right;
}
