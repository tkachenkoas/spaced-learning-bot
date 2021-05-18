package com.atstudio.spacedlearningbot.database.config.migrations;

import com.atstudio.spacedlearningbot.database.entity.base.PrimaryKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface MigrationEntityRepository extends CrudRepository<MigrationEntity, PrimaryKey> {

    List<MigrationEntity> findAllByOwnerId(String partition);

}
