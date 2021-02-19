package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.atstudio.spacedlearningbot.database.IFlashCardsDao;
import com.atstudio.spacedlearningbot.domain.Category;
import com.atstudio.spacedlearningbot.domain.FlashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.atstudio.spacedlearningbot.domain.RepetitionMode.SELF_CHECK;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
class FlashCardsDaoImplTest {

    @Autowired
    private IFlashCardsDao underTest;

    @Test
    void willSaveFlashCard() {
        Category category = new Category().withName("some-name").withAlias("cat-alias");

        FlashCard toSave = new FlashCard()
                .withBiDirectional(true)
                .withLeft("left")
                .withRight("right")
                .withRepetitionMode(SELF_CHECK);

        FlashCard flashCard = underTest.saveFlashCard(category, toSave);

        List<FlashCard> all = underTest.getAllForCategory(category);

        assertThat(all).hasSize(1);
        assertThat(all.get(0)).usingRecursiveComparison()
                .ignoringFields("id").isEqualTo(toSave);
    }



}