package com.atstudio.spacedlearningbot.database.entity.exercise;

import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface ExerciseEntityRepository extends CrudRepository<ExerciseEntity, String> {

    List<ExerciseEntity> findAllByOwnerIdAndNextRepetitionBefore(String ownerId, Instant moment);

}
