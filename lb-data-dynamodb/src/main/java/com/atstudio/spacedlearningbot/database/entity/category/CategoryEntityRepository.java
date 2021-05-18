package com.atstudio.spacedlearningbot.database.entity.category;

import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface CategoryEntityRepository extends CrudRepository<CategoryEntity, PrimaryKey> {

    List<CategoryEntity> findAllByOwnerIdAndEntityIdStartingWith(String ownerId, String entityPrefix);

    Optional<CategoryEntity> findByOwnerIdAndAlias(String ownerId, String alias);

}
