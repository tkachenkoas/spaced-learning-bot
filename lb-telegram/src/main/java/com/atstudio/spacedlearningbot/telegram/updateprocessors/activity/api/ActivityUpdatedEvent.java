package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ActivityUpdatedEvent {
    private Long chatId;
    private CurrentActivity activity;
}
