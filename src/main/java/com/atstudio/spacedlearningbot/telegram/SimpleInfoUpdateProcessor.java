package com.atstudio.spacedlearningbot.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.atstudio.spacedlearningbot.utils.TgBotApiObjectsUtils.geUpdateMessage;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

public abstract class SimpleInfoUpdateProcessor extends AbstractMessageAwareUpdateProcessor {

    @Override
    public boolean applicableFor(Update update) {
        return applicableCommands().contains(
                trimToEmpty(geUpdateMessage(update))
        );
    }

    @Override
    public void processUpdate(Update update) {
        sendMessageToSourceChat(
                update,
                provideMessageText(messageCode())
        );
    }

    abstract protected List<String> applicableCommands();

    abstract protected String messageCode();

}
