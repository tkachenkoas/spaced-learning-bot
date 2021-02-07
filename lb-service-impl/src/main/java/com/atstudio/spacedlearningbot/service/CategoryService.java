package com.atstudio.spacedlearningbot.service;

import com.atstudio.spacedlearningbot.database.ICategoryDAO;
import com.atstudio.spacedlearningbot.domain.Category;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

import static com.atstudio.spacedlearningbot.cache.CacheRegionStrings.CATEGORY_CACHE;

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

    @Override
    public void deleteCategory(Long chatId, String categoryId) {
        categoryDAO.deleteCategory(chatId, categoryId);
    }

    @Override
    @Cacheable(
            cacheNames = CATEGORY_CACHE,
            key = "#chatId+#alias"
    )
    public Category getCategoryByAlias(Long chatId, String alias) {
        return categoryDAO.getCategoryByAlias(chatId, alias)
                .orElseThrow();
    }

    private void validateCategory(Category category) {
        if (StringUtils.isBlank(category.getName())) {
            throw new ValidationException("Category name can not be blank");
        }
    }
}
