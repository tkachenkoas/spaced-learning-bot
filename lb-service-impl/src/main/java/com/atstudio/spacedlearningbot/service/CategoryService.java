package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class CategoryService implements ICategoryService {
    @Override
    public List<Category> getCategoriesForChat(Long chatId) {
        return asList(
                Category.builder().name("First category").chatScopedId("ajhsfla").build(),
                Category.builder().name("Second category").chatScopedId("asdasd").build()
        );
    }
}
