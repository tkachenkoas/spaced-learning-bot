package com.atstudio.spacedlearningbot.database.testconfig;

import lombok.SneakyThrows;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PreDestroy;

@ComponentScan("com.atstudio.spacedlearningbot.database")
@SpringBootConfiguration
public class InMemoryDbTestContext {

    @PreDestroy
    @SneakyThrows
    public void stopServer() {
        DynamoDbStarter.dynamoDbServer().stop();
    }

}
