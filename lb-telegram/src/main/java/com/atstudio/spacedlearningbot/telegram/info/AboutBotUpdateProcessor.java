package com.atstudio.spacedlearningbot.telegram.info;

import com.atstudio.spacedlearningbot.telegram.SimpleInfoUpdateProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;

import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getUpdateChatId;

@AllArgsConstructor
@Component
public class AboutBotUpdateProcessor extends SimpleInfoUpdateProcessor {

    @Override
    public List<String> applicableCommands() {
        return Arrays.asList("/start", "/help", "/about");
    }


    @Override
    public void process(Update update) {
        sendMessage(
                new SendMessage(
                        getUpdateChatId(update), provideMessageText(messageCode())
                ).setParseMode("MarkdownV2")
        );
    }

    @Override
    protected String messageCode() {
        return "about_bot";
    }
}
