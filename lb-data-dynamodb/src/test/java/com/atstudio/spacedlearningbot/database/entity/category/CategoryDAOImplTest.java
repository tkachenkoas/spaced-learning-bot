package com.atstudio.spacedlearningbot.database.entity.category;

import com.atstudio.spacedlearningbot.database.ICategoryDAO;
import com.atstudio.spacedlearningbot.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryDAOImplTest {

    @Autowired
    private ICategoryDAO underTest;

    @Test
    public void willAddCategory() {
        long chat = nextChatId();

        Category category = new Category()
                .withAlias("alias")
                .withName("name");

        Category created = underTest.createCategory(chat, category);

        assertThat(created).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(category);
    }

    @Test
    public void willListCategories() {
        long chat = nextChatId();

        Category first = new Category()
                .withAlias("first-alias")
                .withName("first-name");
        first = underTest.createCategory(chat, first);

        Category second = new Category()
                .withAlias("second-alias")
                .withName("second-name");
        second = underTest.createCategory(chat, second);

        List<Category> categories = underTest.getCategoriesForChat(chat);

        assertThat(categories).containsExactlyInAnyOrder(first, second);
    }

    @Test
    public void willDeleteCategory() {
        long chat = nextChatId();

        Category first = new Category()
                .withAlias("first-alias")
                .withName("first-name");
        underTest.createCategory(chat, first);

        Category second = new Category()
                .withAlias("second-alias")
                .withName("second-name");
        underTest.createCategory(chat, second);

        assertThat(underTest.getCategoriesForChat(chat)).hasSize(2);

        underTest.deleteCategory(chat, first.getAlias());

        List<Category> remaining = underTest.getCategoriesForChat(chat);
        assertThat(remaining).hasSize(1).contains(second);
    }

    @Test
    public void willGetByAlias() {
        long chat = nextChatId();

        Category first = new Category()
                .withAlias("alias")
                .withName("name");
        first = underTest.createCategory(chat, first);

        Optional<Category> category = underTest.getCategoryByAlias(chat, "alias");

        assertThat(category.get()).isEqualTo(first);
    }

    private long nextChatId() {
        return new Random().nextLong();
    }

}