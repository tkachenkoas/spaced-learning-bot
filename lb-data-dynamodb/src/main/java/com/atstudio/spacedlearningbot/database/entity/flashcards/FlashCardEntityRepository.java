package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import org.springframework.data.repository.CrudRepository;

public interface FlashCardEntityRepository extends CrudRepository<FlashCardEntity, PrimaryKey> {

}
