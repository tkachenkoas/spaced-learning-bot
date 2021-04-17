package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.domain.Category;

import java.util.List;

public interface ICategoryService {

    Category createCategory(Category category);

    List<Category> getCategories(String ownerId);

    void deleteCategory(String ownerId, String categoryId);

    Category getCategoryByAlias(String ownerId, String categoryId);
}
