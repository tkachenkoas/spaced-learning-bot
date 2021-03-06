package com.atstudio.spacedlearningbot.telegram.updateprocessors.command;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface DirectCommandUpdateProcessor {

    List<String> applicableCommands();

    void process(Update update);

}
