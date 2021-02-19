package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.atstudio.spacedlearningbot.database.IFlashCardsDao;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.atstudio.spacedlearningbot.database.entity.flashcards.FlashCardToEntityMapper.toFlashCard;

@Repository
@RequiredArgsConstructor
public class FlashCardsDaoImpl implements IFlashCardsDao {

    private final FlashCardEntityRepository repository;

    @Override
    public FlashCard saveFlashCard(Category category, FlashCard flashCard) {
        FlashCardEntity entity = FlashCardToEntityMapper.toEntity(category, flashCard);
        FlashCardEntity saved = repository.save(entity);
        return toFlashCard(saved);
    }

    @Override
    public List<FlashCard> getAllForCategory(Category category) {
        List<FlashCardEntity> entities = repository.findAllByCategoryAlias(category.getAlias());
        return entities.stream()
                .map(FlashCardToEntityMapper::toFlashCard)
                .collect(Collectors.toList());
    }
}
