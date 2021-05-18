package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlashCardEntityRepository extends CrudRepository<FlashCardEntity, PrimaryKey> {

    List<FlashCardEntity> findAllByOwnerIdAndCategoryId(String ownerId, String categoryId);

}
