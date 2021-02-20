package com.atstudio.spacedlearningbot.database.entity;

import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import lombok.SneakyThrows;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class DynamoDbStarter implements BeanFactoryPostProcessor {

    @Override
    @SneakyThrows
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        dynamoDbServer().start();
    }

    public static DynamoDBProxyServer dynamoDbServer() throws ParseException {
        return ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", "8084"});
    }

}
