package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ActivityFinishedEvent {
    private Long chatId;
}
