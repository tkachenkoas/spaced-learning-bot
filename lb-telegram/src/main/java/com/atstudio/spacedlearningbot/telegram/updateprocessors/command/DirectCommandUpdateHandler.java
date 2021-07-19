package com.atstudio.spacedlearningbot.telegram.updateprocessors.command;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityFinishedEvent;
import com.github.tkachenkoas.telegramstarter.RootUpdateHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getMessageText;


@Component
@Slf4j
@AllArgsConstructor
public class DirectCommandUpdateHandler implements RootUpdateHandler {

    private final List<DirectCommandUpdateProcessor> directCommandUpdateProcessors;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public boolean applicableFor(Update update) {
        return StringUtils.startsWith(getMessageText(update), "/");
    }

    @Override
    public void handle(Update update) {
        String directCommand = getMessageText(update);
        for (DirectCommandUpdateProcessor commandUpdateProcessor : directCommandUpdateProcessors) {
            List<String> commands = commandUpdateProcessor.applicableCommands();
            if (commands.contains(directCommand)) {
                // any direct command execution will abort previous activity
                eventPublisher.publishEvent(
                        new ActivityFinishedEvent(getChatId(update))
                );
                commandUpdateProcessor.process(update);
                return;
            }
        }
        log.warn("Wasn't able to process direct command {} from update {}", directCommand, update);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
