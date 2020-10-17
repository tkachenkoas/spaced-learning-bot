package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ActivityCallback {
    private ActivityType activityType;
    private String payload;
}
