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
import java.util.stream.Collectors;

import static com.atstudio.spacedlearningbot.database.entity.flashcards.FlashCardToEntityMapper.toFlashCard;
import static com.atstudio.spacedlearningbot.database.entity.flashcards.FlashCardsForCategoryIndexExecutor.FLASHCARDS_FOR_CATEGORY_INDEX;

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

        return list.stream().map(FlashCardToEntityMapper::toFlashCard)
                .collect(Collectors.toList());
    }
}
