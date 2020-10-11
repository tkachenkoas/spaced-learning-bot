package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity;

import lombok.Value;

import java.util.Map;

@Value
public class CurrentActivity {
    ActivityType activityType;
    Map<String, Object> details;
}
