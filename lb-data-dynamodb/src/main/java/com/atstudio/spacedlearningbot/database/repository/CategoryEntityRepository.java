package com.atstudio.spacedlearningbot.database.repository;

import com.atstudio.spacedlearningbot.database.entity.CategoryEntity;
import com.atstudio.spacedlearningbot.database.entity.CategoryEntityPrimaryKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryEntityRepository extends CrudRepository<CategoryEntity, CategoryEntityPrimaryKey> {

    List<CategoryEntity> findAllByChatId(Long chatId);

    void deleteByChatIdAndAlias(Long chatId, String alias);

    Optional<CategoryEntity> findByChatIdAndAlias(Long chatId, String alias);
}
