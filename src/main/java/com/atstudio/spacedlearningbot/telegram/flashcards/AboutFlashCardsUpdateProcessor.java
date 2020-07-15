package com.atstudio.spacedlearningbot.telegram.flashcards;

import com.atstudio.spacedlearningbot.telegram.SimpleInfoUpdateProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class AboutFlashCardsUpdateProcessor extends SimpleInfoUpdateProcessor {

    @Override
    protected List<String> applicableCommands() {
        return asList("/flashcards");
    }

    @Override
    protected String messageCode() {
        return "about_flashcards";
    }
}
