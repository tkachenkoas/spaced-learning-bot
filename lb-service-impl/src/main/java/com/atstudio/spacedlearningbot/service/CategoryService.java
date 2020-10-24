package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.database.ICategoryDAO;
import com.atstudio.spacedlearningbot.domain.Category;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryDAO categoryDAO;

    @Override
    public List<Category> getCategoriesForChat(Long chatId) {
        return categoryDAO.getCategoriesForChat(chatId);
    }

    @Override
    public Category createCategory(Long chatId, Category category) {
        validateCategory(category);
        category.setName(category.getName().trim());
        return categoryDAO.createCategory(chatId, category);
    }

    private void validateCategory(Category category) {
        if (StringUtils.isBlank(category.getName())) {
            throw new ValidationException("Category name can not be blank");
        }
    }
}
