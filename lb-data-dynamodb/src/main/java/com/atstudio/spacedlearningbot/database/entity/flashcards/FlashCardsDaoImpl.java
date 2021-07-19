package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.atstudio.spacedlearningbot.database.IFlashCardsDao;
import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.atstudio.spacedlearningbot.database.entity.flashcards.FlashCardToEntityMapper.toFlashCard;
import static com.atstudio.spacedlearningbot.database.entity.flashcards.FlashCardToEntityMapper.toPrimaryKey;
import static com.atstudio.spacedlearningbot.database.entity.flashcards.FlashCardsForCategoryIndexExecutor.FLASHCARDS_FOR_CATEGORY_INDEX;
import static com.atstudio.spacedlearningbot.util.BotCollectionUtils.convertList;
import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class FlashCardsDaoImpl implements IFlashCardsDao {

    private final IDynamoDBMapper dbMapper;
    private final FlashCardEntityRepository repository;

    @Override
    public FlashCard saveFlashCard(Category category, FlashCard flashCard) {
        FlashCardEntity entity = FlashCardToEntityMapper.toEntity(category, flashCard);
        FlashCardEntity saved = repository.save(entity);
        return toFlashCard(saved);
    }

    @Override
    public List<FlashCard> getAllForCategory(Category category) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(category.getOwnerId()));
        eav.put(":val2", new AttributeValue().withS(category.getId()));

        DynamoDBQueryExpression<FlashCardEntity> query = new DynamoDBQueryExpression<FlashCardEntity>()
                .withConsistentRead(false)
                .withIndexName(FLASHCARDS_FOR_CATEGORY_INDEX)
                .withKeyConditionExpression(MessageFormat.format(
                        "{0} = :val1 AND {1} = :val2",
                        PrimaryKey.ATTRIBUTE_OWNER_ID, FlashCardEntity.ATTRIBUTE_CATEGORY_ID
                ))
                .withExpressionAttributeValues(eav);

        PaginatedQueryList<FlashCardEntity> list = dbMapper.query(FlashCardEntity.class, query);

        return convertList(list, FlashCardToEntityMapper::toFlashCard);
    }

    @Override
    public List<FlashCard> getFlashcardsByIds(String ownerId, List<String> flashCardIds) {
        List<PrimaryKey> primaryKeys = flashCardIds.stream()
                .map(entityId -> toPrimaryKey(ownerId, entityId))
                .collect(toList());
        return convertList(repository.findAllById(primaryKeys), FlashCardToEntityMapper::toFlashCard);
    }
}
