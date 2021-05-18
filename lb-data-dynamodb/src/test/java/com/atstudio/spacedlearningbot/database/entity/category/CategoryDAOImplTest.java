package com.atstudio.spacedlearningbot.database.entity.category;

import com.atstudio.spacedlearningbot.database.ICategoryDAO;
import com.atstudio.spacedlearningbot.database.testconfig.InMemoryDbTestContext;
import com.atstudio.spacedlearningbot.domain.Category;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = InMemoryDbTestContext.class)
class CategoryDAOImplTest {

    @Autowired
    private ICategoryDAO underTest;

    @Test
    public void willAddCategory() {
        String owner = nextOwnerId();

        Category category = new Category()
                .withOwnerId(owner)
                .withAlias("alias")
                .withName("name");

        Category created = underTest.createCategory(category);

        assertThat(created).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(category);
    }

    @Test
    public void willListCategories() {
        String owner = nextOwnerId();

        Category first = new Category()
                .withOwnerId(owner)
                .withAlias("first-alias")
                .withName("first-name");
        first = underTest.createCategory(first);

        Category second = new Category()
                .withOwnerId(owner)
                .withAlias("second-alias")
                .withName("second-name");
        second = underTest.createCategory(second);

        List<Category> categories = underTest.getCategoriesForUser(owner);

        assertThat(categories).containsExactlyInAnyOrder(first, second);
    }

    @Test
    public void willDeleteCategory() {
        String owner = nextOwnerId();

        Category first = new Category()
                .withAlias("first-alias")
                .withOwnerId(owner)
                .withName("first-name");
        first = underTest.createCategory(first);

        Category second = new Category()
                .withOwnerId(owner)
                .withAlias("second-alias")
                .withName("second-name");
        second = underTest.createCategory(second);

        assertThat(underTest.getCategoriesForUser(owner)).hasSize(2);

        underTest.deleteCategory(owner, first.getId());

        List<Category> remaining = underTest.getCategoriesForUser(owner);
        assertThat(remaining).hasSize(1).contains(second);
    }

    @Test
    public void willGetByAlias() {
        String owner = nextOwnerId();

        Category first = new Category()
                .withOwnerId(owner)
                .withAlias("alias")
                .withName("name");
        first = underTest.createCategory(first);

        Optional<Category> category = underTest.getCategoryByAlias(owner, "alias");

        assertThat(category.get()).isEqualTo(first);
    }

    private String nextOwnerId() {
        return RandomStringUtils.randomAlphabetic(5);
    }

}