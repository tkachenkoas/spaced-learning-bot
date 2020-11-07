package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.domain.Category;

import java.util.List;

public interface ICategoryDAO {

    Category createCategory(Long chatId, Category category);

    List<Category> getCategoriesForChat(Long chatId);

    void deleteCategory(Long chatId, String categoryId);
}
