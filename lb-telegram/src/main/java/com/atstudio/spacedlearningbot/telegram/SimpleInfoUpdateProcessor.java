package com.atstudio.spacedlearningbot.telegram;

import com.atstudio.spacedlearningbot.utils.TgBotApiObjectsUtils;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public abstract class SimpleInfoUpdateProcessor extends AbstractMessageAwareUpdateProcessor {

    @Override
    public boolean applicableFor(Update update) {
        return applicableCommands().contains(
                StringUtils.trimToEmpty(TgBotApiObjectsUtils.geUpdateMessage(update))
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
