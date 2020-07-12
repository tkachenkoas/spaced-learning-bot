package com.atstudio.spacedlearningbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class SpacedlearningbotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(SpacedlearningbotApplication.class, args);
	}

}
