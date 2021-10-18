package com.yasuodemo.backend.controller.dogListener;

import com.yasuodemo.backend.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class ShowListener implements MessageListener {
    private DogService dogService;
    private RabbitTemplate template;
    @Override
    public void onMessage(Message message) {
        Object rawMessage = template.getMessageConverter().fromMessage(message);
        System.out.println(rawMessage);
    }
}
