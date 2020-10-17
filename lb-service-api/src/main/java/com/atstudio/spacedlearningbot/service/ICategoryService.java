package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.domain.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategoriesForChat(Long chatId);
}
