package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ActivityInitializer extends ActivityTypeApplicable {
    void initActivity(Update update, CurrentActivity currentActivity);
}
