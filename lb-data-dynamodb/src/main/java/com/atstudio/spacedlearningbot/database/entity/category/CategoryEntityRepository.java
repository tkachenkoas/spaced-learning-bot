package com.atstudio.spacedlearningbot.database.entity.category;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface CategoryEntityRepository extends CrudRepository<CategoryEntity, String> {

    List<CategoryEntity> findAllByOwnerId(String ownerId);

    void deleteByOwnerIdAndAlias(String ownerId, String alias);

    Optional<CategoryEntity> findByOwnerIdAndAlias(String ownerId, String alias);
}
