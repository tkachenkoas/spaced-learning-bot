package com.atstudio.spacedlearningbot.updateprocessors.info;

import com.atstudio.spacedlearningbot.service.BotMessageProvider;
import com.atstudio.telegrambot.starterpack.api.TgApiExecutor;
import com.atstudio.telegrambot.starterpack.api.UpdateProcessor;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;

import static com.atstudio.spacedlearningbot.utils.TgBotApiObjectsUtils.geUpdateMessage;
import static com.atstudio.spacedlearningbot.utils.TgBotApiObjectsUtils.getUpdateChatId;

@AllArgsConstructor
@Component
public class AboutUpdateProcessor implements UpdateProcessor {

    private final static List<String> APPLICABLE_COMMANDS = Arrays.asList("/start", "/help", "/about");

    private final BotMessageProvider botMessageProvider;
    private final TgApiExecutor tgApiExecutor;

    @Override
    public boolean applicableFor(Update update) {
        String message = StringUtils.trimToEmpty(geUpdateMessage(update));
        return APPLICABLE_COMMANDS.contains(message);
    }

    @Override
    public void processUpdate(Update update) {
        tgApiExecutor.execute(
                new SendMessage(
                        getUpdateChatId(update),
                        botMessageProvider.getMessage("about.bot")
                )
        );
    }
}
