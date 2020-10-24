package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain;

import lombok.*;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@With
@NoArgsConstructor
public class CurrentActivity {
    private ActivityType activityType;
    private Map<String, Object> details;
}