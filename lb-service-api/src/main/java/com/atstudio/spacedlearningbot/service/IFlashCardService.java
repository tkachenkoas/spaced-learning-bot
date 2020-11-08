package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;

public interface IFlashCardService {

    void addFlashCardToCategory(Category category, FlashCard flashCard);

}
