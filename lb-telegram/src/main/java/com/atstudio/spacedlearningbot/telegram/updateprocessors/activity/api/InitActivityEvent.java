package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@AllArgsConstructor
@Getter
public class InitActivityEvent {
    private ActivityType activityType;
    private Update update;
}
