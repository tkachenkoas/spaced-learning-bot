package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.atstudio.spacedlearningbot.database.entity.base.EntityIdMapper;
import com.atstudio.spacedlearningbot.database.entity.base.EntityType;
import com.atstudio.spacedlearningbot.database.entity.base.IdentifierGenerator;
import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;

import static com.atstudio.spacedlearningbot.database.enummapper.RepetitionModeToCodeMapper.getCode;
import static com.atstudio.spacedlearningbot.database.enummapper.RepetitionModeToCodeMapper.getMode;
import static java.util.Objects.requireNonNullElseGet;

class FlashCardToEntityMapper {

    static FlashCardEntity toEntity(Category category, FlashCard flashCard) {
        FlashCardEntity result = new FlashCardEntity();
        String flashCardId = requireNonNullElseGet(
                flashCard.getId(), IdentifierGenerator::longId
        );
        result.setPrimaryKey(
                new PrimaryKey(
                        category.getOwnerId(),
                        EntityIdMapper.withPrefix(EntityType.FLASHCARD, flashCardId)
                )
        );
        result.setBiDirectional(flashCard.isBiDirectional());
        result.setCategoryId(category.getId());
        result.setType(getCode(flashCard.getType()));
        result.setLeft(flashCard.getLeft());
        result.setRight(flashCard.getRight());
        return result;
    }

    public static FlashCard toFlashCard(FlashCardEntity entity) {
        FlashCard flashCard = new FlashCard();
        flashCard.setLeft(entity.getLeft());
        flashCard.setRight(entity.getRight());
        flashCard.setBiDirectional(entity.isBiDirectional());
        flashCard.setId(EntityIdMapper.extractId(
                entity.getEntityId(), EntityType.FLASHCARD
        ));
        flashCard.setType(
                getMode(entity.getType())
        );
        return flashCard;
    }
}
