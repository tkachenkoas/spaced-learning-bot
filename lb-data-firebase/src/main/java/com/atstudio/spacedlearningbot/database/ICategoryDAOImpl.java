package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.database.entity.CategoryEntity;
import com.atstudio.spacedlearningbot.domain.Category;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class ICategoryDAOImpl implements ICategoryDAO {

    private final Firestore firestore;

    @Override
    @SneakyThrows
    public Category createCategory(Long chatId, Category category) {
        CategoryEntity entity = CategoryEntity.fromCategory(category, chatId);
        getCategoriesCollection().document(entity.getId()).set(entity)
                .get();
        return entity.toCategory();
    }

    @Override
    @SneakyThrows
    public List<Category> getCategoriesForChat(Long chatId) {
        Query query = getCategoriesCollection().whereEqualTo("chatId", chatId);
        List<QueryDocumentSnapshot> documents = query.get().get().getDocuments();
        return documents.stream()
                .map(doc -> doc.toObject(CategoryEntity.class))
                .map(CategoryEntity::toCategory)
                .collect(toList());
    }

    private CollectionReference getCategoriesCollection() {
        return firestore.collection("categories");
    }
}
