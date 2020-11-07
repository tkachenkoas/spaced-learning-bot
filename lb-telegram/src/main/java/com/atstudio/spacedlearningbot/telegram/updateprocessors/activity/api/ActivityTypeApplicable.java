package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;

public interface ActivityTypeApplicable {
    ActivityType applicableForActivityType();
}
