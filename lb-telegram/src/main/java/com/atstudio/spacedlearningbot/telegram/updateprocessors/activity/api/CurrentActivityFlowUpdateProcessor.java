package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CurrentActivityFlowUpdateProcessor extends ActivityTypeApplicable {
    void processUpdateForCurrentActivity(Update update, CurrentActivity currentActivity);
}
