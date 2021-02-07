package com.atstudio.spacedlearningbot.database.repository;

import com.atstudio.spacedlearningbot.database.ICategoryDAO;
import com.atstudio.spacedlearningbot.database.entity.CategoryEntity;
import com.atstudio.spacedlearningbot.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class ICategoryDAOImpl implements ICategoryDAO {

    private final CategoryEntityRepository repository;

    @Override
    @SneakyThrows
    public Category createCategory(Long chatId, Category category) {
        CategoryEntity entity = CategoryEntity.fromCategory(category, chatId);
        return repository.save(entity).toCategory();
    }

    @Override
    @SneakyThrows
    public List<Category> getCategoriesForChat(Long chatId) {
        List<CategoryEntity> categories = repository.findAllByChatId(chatId);
        return categories.stream()
                .map(CategoryEntity::toCategory)
                .collect(toList());
    }

    @Override
    public Optional<Category> getCategoryByAlias(Long chatId, String alias) {
        return repository.findByChatIdAndAlias(chatId, alias)
                .map(CategoryEntity::toCategory);
    }

    @Override
    @SneakyThrows
    public void deleteCategory(Long chatId, String alias) {
        repository.deleteByChatIdAndAlias(chatId, alias);
    }

}
