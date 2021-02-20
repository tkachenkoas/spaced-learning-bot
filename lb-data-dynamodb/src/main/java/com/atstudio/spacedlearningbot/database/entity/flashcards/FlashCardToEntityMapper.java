package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;

import static com.atstudio.spacedlearningbot.database.entity.flashcards.RepetitionModeToCodeMapper.getCode;
import static com.atstudio.spacedlearningbot.database.entity.flashcards.RepetitionModeToCodeMapper.getMode;

class FlashCardToEntityMapper {

    static FlashCardEntity toEntity(Category category, FlashCard flashCard) {
        FlashCardEntity result = new FlashCardEntity();
        result.setFlashCardId(flashCard.getId());
        result.setBiDirectional(flashCard.isBiDirectional());
        result.setCategoryAlias(category.getAlias());
        result.setRepetitionMode(getCode(flashCard.getRepetitionMode()));
        result.setLeft(flashCard.getLeft());
        result.setRight(flashCard.getRight());
        return result;
    }

    public static FlashCard toFlashCard(FlashCardEntity entity) {
        FlashCard flashCard = new FlashCard();
        flashCard.setLeft(entity.getLeft());
        flashCard.setRight(entity.getRight());
        flashCard.setBiDirectional(entity.isBiDirectional());
        flashCard.setId(entity.getFlashCardId());
        flashCard.setRepetitionMode(
                getMode(entity.getRepetitionMode())
        );
        return flashCard;
    }
}
