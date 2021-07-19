package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.Exercise;
import com.atstudio.spacedlearningbot.domain.FlashCard;

import java.util.List;

public interface IFlashCardService {

    void addFlashCardToCategory(Category category, FlashCard flashCard);

    List<Exercise> getExercisesAvailableForRepetition(String ownerId);

    List<FlashCard> getFlashcards(String ownerId, List<String> flashCardIds);

}
