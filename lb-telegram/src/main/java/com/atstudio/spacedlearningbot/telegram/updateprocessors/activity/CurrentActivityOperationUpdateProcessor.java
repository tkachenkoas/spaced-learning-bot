package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CurrentActivityOperationUpdateProcessor {
    boolean applicableFor(CurrentActivity operation);

    void process(Update update);
}
