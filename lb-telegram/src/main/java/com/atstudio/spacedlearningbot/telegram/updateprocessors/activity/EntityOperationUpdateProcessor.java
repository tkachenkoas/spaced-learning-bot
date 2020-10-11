package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface EntityOperationUpdateProcessor {
    boolean applicableFor(CurrentActivity operation);

    void process(Update update);
}
