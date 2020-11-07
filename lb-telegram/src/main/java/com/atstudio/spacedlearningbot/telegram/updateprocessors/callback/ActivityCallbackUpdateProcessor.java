package com.atstudio.spacedlearningbot.telegram.updateprocessors.callback;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityCallback;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ActivityCallbackUpdateProcessor {

    boolean applicableFor(ActivityCallback callback);

    void process(Update update, ActivityCallback activityCallback);
}
