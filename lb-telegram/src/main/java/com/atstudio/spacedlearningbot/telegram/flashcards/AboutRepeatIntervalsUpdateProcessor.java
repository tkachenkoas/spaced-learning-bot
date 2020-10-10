package com.atstudio.spacedlearningbot.telegram.flashcards;

import com.atstudio.spacedlearningbot.telegram.SimpleInfoUpdateProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class AboutRepeatIntervalsUpdateProcessor extends SimpleInfoUpdateProcessor {

    @Override
    public List<String> applicableCommands() {
        return asList("/about_flashcards_intervals -");
    }

    @Override
    protected String messageCode() {
        return "about_flashcards";
    }
}
