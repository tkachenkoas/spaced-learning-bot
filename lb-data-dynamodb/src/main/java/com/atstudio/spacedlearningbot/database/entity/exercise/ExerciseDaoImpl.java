package com.atstudio.spacedlearningbot.database.entity.exercise;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.atstudio.spacedlearningbot.commons.CurrentTimeProvider;
import com.atstudio.spacedlearningbot.database.IExerciseDao;
import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import com.atstudio.spacedlearningbot.domain.Exercise;
import lombok.RequiredArgsConstructor;
import org.socialsignin.spring.data.dynamodb.marshaller.Instant2IsoDynamoDBMarshaller;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.atstudio.spacedlearningbot.database.entity.exercise.ExerciseEntityConverter.toExerciseList;
import static com.atstudio.spacedlearningbot.database.entity.exercise.ExerciseForRepetitionIndexExecutor.EXERCISES_BY_REPETITION_INDEX;
import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class ExerciseDaoImpl implements IExerciseDao {

    private static final Instant2IsoDynamoDBMarshaller MARSHALLER = new Instant2IsoDynamoDBMarshaller();

    private final IDynamoDBMapper dbMapper;
    private final ExerciseEntityRepository repository;
    private final CurrentTimeProvider currentTimeProvider;

    @Override
    public List<Exercise> save(List<Exercise> exercises, String ownerId) {
        List<ExerciseEntity> entities = exercises.stream()
                .map(exercise -> ExerciseEntityConverter.toEntity(exercise, ownerId))
                .collect(toList());

        return toExerciseList(repository.saveAll(entities));
    }

    @Override
    public List<Exercise> loadExercisesAwaitingRepetition(String ownerId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(ownerId));
        eav.put(":val2", new AttributeValue().withS(
                MARSHALLER.convert(currentTimeProvider.getCurrentTime())
        ));

        DynamoDBQueryExpression<ExerciseEntity> query = new DynamoDBQueryExpression<ExerciseEntity>()
                .withConsistentRead(false)
                .withIndexName(EXERCISES_BY_REPETITION_INDEX)
                .withKeyConditionExpression(MessageFormat.format(
                        "{0} = :val1 AND {1} < :val2",
                        PrimaryKey.ATTRIBUTE_OWNER_ID, ExerciseEntity.ATTRIBUTE_NEXT_REPETITION
                ))
                .withExpressionAttributeValues(eav);

        PaginatedQueryList<ExerciseEntity> list = dbMapper.query(ExerciseEntity.class, query);

        return list.stream().map(ExerciseEntityConverter::toExercise)
                .collect(Collectors.toList());
    }
}
