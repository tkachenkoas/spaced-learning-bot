package com.atstudio.spacedlearningbot.database.entity;

import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class DynamoDbStarter implements BeanFactoryPostProcessor {

    private static DynamoDBProxyServer sServer;

    @Override
    @SneakyThrows
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final String[] localArgs = {"-inMemory", "-port", "8084"};
        sServer = ServerRunner.createServerFromCommandLineArgs(localArgs);
        sServer.start();
    }

    @PreDestroy
    @SneakyThrows
    public void shutdownDynamoDB() {
        sServer.stop();
    }

}
