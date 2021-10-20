package com.yasuodemo.backend.config;

import com.yasuodemo.backend.controller.dogListener.SaveListener;
import com.yasuodemo.backend.controller.dogListener.ShowListener;
import com.yasuodemo.backend.service.DogService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig extends config.MQConfig {

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public MessageListenerContainer saveListenerContainer(ConnectionFactory connectionFactory,DogService dogService, RabbitTemplate rabbitTemplate) {
        SimpleMessageListenerContainer container = containerGenerator(connectionFactory,rabbitConstants().saveQueue);
        container.setMessageListener(new SaveListener(dogService,rabbitTemplate,rabbitConstants()));
        return container;
    }

    @Bean @Autowired
    public MessageListenerContainer showListenerContainer(ConnectionFactory connectionFactory,DogService dogService, RabbitTemplate rabbitTemplate) {
        SimpleMessageListenerContainer container = containerGenerator(connectionFactory,rabbitConstants().showQueue);
        container.setMessageListener(new ShowListener(dogService,rabbitTemplate,rabbitConstants()));
        return container;
    }

    private SimpleMessageListenerContainer containerGenerator(ConnectionFactory connectionFactory,String queueName){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        return simpleMessageListenerContainer;
    }
}
