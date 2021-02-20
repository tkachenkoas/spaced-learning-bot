package com.atstudio.spacedlearningbot.database.entity.category;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface CategoryEntityRepository extends CrudRepository<CategoryEntity, String> {

    List<CategoryEntity> findAllByChatId(Long chatId);

    void deleteByChatIdAndAlias(Long chatId, String alias);

    Optional<CategoryEntity> findByChatIdAndAlias(Long chatId, String alias);
}
