package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.domain.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryDAO {

    Category createCategory(Long chatId, Category category);

    List<Category> getCategoriesForChat(Long chatId);

    Optional<Category> getCategoryByChatScopedId(Long chatId, String categoryId);

    void deleteCategory(Long chatId, String categoryId);
}
