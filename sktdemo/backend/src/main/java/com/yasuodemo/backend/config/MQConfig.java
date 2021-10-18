package com.yasuodemo.backend.config;

import com.yasuodemo.backend.controller.dogListener.SaveListener;
import com.yasuodemo.backend.service.DogService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,DogService dogService, RabbitTemplate rabbitTemplate) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(rabbitConstants().saveQueue);
        simpleMessageListenerContainer.setMessageListener(new SaveListener(dogService,rabbitTemplate));
        return simpleMessageListenerContainer;

    }

}
