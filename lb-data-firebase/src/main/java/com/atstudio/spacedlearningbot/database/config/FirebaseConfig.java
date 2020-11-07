package com.atstudio.spacedlearningbot.database.config;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() {
        var options = FirestoreOptions.getDefaultInstance()
                .toBuilder()
                .setProjectId("spaced-learning-bot");
        return options.build().getService();
    }

}
