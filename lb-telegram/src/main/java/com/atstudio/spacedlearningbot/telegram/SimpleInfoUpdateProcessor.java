package com.atstudio.spacedlearningbot.telegram;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.command.DirectCommandUpdateProcessor;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class SimpleInfoUpdateProcessor extends AbstractMessageHandler implements DirectCommandUpdateProcessor {

    @Override
    public void process(Update update) {
        sendMessageToSourceChat(
                update,
                provideMessageText(messageCode())
        );
    }

    abstract protected String messageCode();

}
