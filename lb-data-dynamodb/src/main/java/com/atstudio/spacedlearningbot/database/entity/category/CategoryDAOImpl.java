package com.atstudio.spacedlearningbot.database.entity.category;

import com.atstudio.spacedlearningbot.database.ICategoryDAO;
import com.atstudio.spacedlearningbot.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.atstudio.spacedlearningbot.database.entity.category.CategoryToEntityMapper.fromCategory;
import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class CategoryDAOImpl implements ICategoryDAO {

    private final CategoryEntityRepository repository;

    @Override
    @SneakyThrows
    public Category createCategory(Category category) {
        CategoryEntity entity = fromCategory(category);
        return CategoryToEntityMapper.toCategory(
                repository.save(entity)
        );
    }

    @Override
    @SneakyThrows
    public List<Category> getCategoriesForUser(String ownerId) {
        List<CategoryEntity> categories = repository.findAllByOwnerId(ownerId);
        return categories.stream()
                .map(CategoryToEntityMapper::toCategory)
                .collect(toList());
    }

    @Override
    public Optional<Category> getCategoryByAlias(String ownerId, String alias) {
        return repository.findByOwnerIdAndAlias(ownerId, alias)
                .map(CategoryToEntityMapper::toCategory);
    }

    @Override
    @SneakyThrows
    public void deleteCategory(String ownerId, String alias) {
        repository.deleteByOwnerIdAndAlias(ownerId, alias);
    }

}
