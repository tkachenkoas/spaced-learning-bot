package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.domain.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryDAO {

    Category createCategory(Category category);

    List<Category> getCategoriesForUser(String ownerId);

    Optional<Category> getCategoryByAlias(String ownerId, String alias);

    void deleteCategory(String ownerId, String alias);
}
