package com.atstudio.spacedlearningbot.database.repository;

import com.atstudio.spacedlearningbot.database.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryEntityRepository extends CrudRepository<CategoryEntity, String> {

    List<CategoryEntity> findAllByChatId(Long chatId);

    void deleteByChatIdAndId(Long chatId, String categoryId);

}
