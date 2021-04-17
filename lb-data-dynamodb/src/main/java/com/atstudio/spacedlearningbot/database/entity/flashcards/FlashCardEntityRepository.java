package com.atstudio.spacedlearningbot.database.entity.flashcards;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlashCardEntityRepository extends CrudRepository<FlashCardEntity, String> {

    List<FlashCardEntity> findAllByCategoryId(String categoryAlias);

}
