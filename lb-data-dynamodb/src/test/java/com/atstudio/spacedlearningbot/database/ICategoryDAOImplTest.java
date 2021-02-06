package com.atstudio.spacedlearningbot.database;

import com.atstudio.spacedlearningbot.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ICategoryDAOImplTest {

    @Autowired
    private ICategoryDAO underTest;

    @Test
    public void willAddCategory() {
        Category category = new Category()
                .withChatScopedId("chat-scoped-id")
                .withName("name");

        Category created = underTest.createCategory(123L, category);

        assertThat(created.getId()).isNotNull();
        assertThat(created).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(category);
    }

}