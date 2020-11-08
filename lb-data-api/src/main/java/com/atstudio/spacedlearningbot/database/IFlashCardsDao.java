package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;

public interface IFlashCardsDao {
    FlashCard saveFlashCard(Category category, FlashCard flashCard);
}
