package com.atstudio.spacedlearningbot.database.entity.exercise;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.atstudio.spacedlearningbot.database.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.socialsignin.spring.data.dynamodb.marshaller.Instant2IsoDynamoDBMarshaller;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExerciseEntity extends BaseEntity {

    static final String ATTRIBUTE_FLASHCARD_ID = "Flashcard_Id";
    static final String ATTRIBUTE_DIRECTION_CODE = "Direction_Code";
    static final String ATTRIBUTE_EXERCISE_LEVEL = "Exercise_Level";
    static final String ATTRIBUTE_NEXT_REPETITION = "Next_Repetition";

    @DynamoDBAttribute(attributeName = ATTRIBUTE_FLASHCARD_ID)
    private String flashCardId;

    @DynamoDBAttribute(attributeName = ATTRIBUTE_DIRECTION_CODE)
    private Integer directionCode;

    @DynamoDBAttribute(attributeName = ATTRIBUTE_EXERCISE_LEVEL)
    private Integer level;

    @DynamoDBAttribute(attributeName = ATTRIBUTE_NEXT_REPETITION)
    @DynamoDBTypeConverted(converter = Instant2IsoDynamoDBMarshaller.class)
    private Instant nextRepetition;

}
