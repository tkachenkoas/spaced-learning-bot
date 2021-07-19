package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;

import java.util.List;

public interface IFlashCardsDao {

    FlashCard saveFlashCard(Category category, FlashCard flashCard);

    List<FlashCard> getAllForCategory(Category category);

    List<FlashCard> getFlashcardsByIds(String ownerId, List<String> flashCardIds);
}
