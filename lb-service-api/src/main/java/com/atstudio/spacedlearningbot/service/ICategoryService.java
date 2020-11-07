package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.domain.Category;

import java.util.List;

public interface ICategoryService {

    Category createCategory(Long chatId, Category category);

    List<Category> getCategoriesForChat(Long chatId);

    void deleteCategory(Long chatId, String categoryId);
}
