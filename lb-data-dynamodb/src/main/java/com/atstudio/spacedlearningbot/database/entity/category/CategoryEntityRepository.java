package com.atstudio.spacedlearningbot.database.entity.category;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryEntityRepository extends CrudRepository<CategoryEntity, CategoryEntityPrimaryKey> {

    List<CategoryEntity> findAllByChatId(Long chatId);

    void deleteByChatIdAndAlias(Long chatId, String alias);

    Optional<CategoryEntity> findByChatIdAndAlias(Long chatId, String alias);
}
