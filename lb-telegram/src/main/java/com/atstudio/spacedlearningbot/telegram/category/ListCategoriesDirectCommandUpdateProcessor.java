package com.atstudio.spacedlearningbot.telegram.category;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.command.DirectCommandUpdateProcessor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class ListCategoriesDirectCommandUpdateProcessor implements DirectCommandUpdateProcessor {

    @Override
    public List<String> applicableCommands() {
        return asList("flashcards_list");
    }

    @Override
    public void process(Update update) {

    }
}
