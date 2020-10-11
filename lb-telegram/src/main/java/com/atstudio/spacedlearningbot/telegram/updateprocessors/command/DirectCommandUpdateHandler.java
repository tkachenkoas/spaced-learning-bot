package com.atstudio.spacedlearningbot.telegram.updateprocessors.command;

import com.atstudio.telegrambot.starterpack.api.UpdateProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getUpdateMessage;


@Component
@Slf4j
@AllArgsConstructor
public class DirectCommandUpdateHandler implements UpdateProcessor {

    private final List<DirectCommandUpdateProcessor> directCommandUpdateProcessors;

    @Override
    public boolean applicableFor(Update update) {
        return StringUtils.startsWith(getUpdateMessage(update), "/");
    }

    @Override
    public void processUpdate(Update update) {
        String directCommand = extractCommand(update);
        for (DirectCommandUpdateProcessor commandUpdateProcessor : directCommandUpdateProcessors) {
            List<String> commands = commandUpdateProcessor.applicableCommands();
            if (commands.contains(directCommand)) {
                commandUpdateProcessor.process(update);
                return;
            }
        }
        log.warn("Wasn't able to process direct command {} from update {}", directCommand, update);
    }

    private String extractCommand(Update update) {
        return getUpdateMessage(update).replace("/", "").split(" ")[0];
    }
}
