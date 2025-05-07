package com.yasuodemo.backend.controller;

import com.yasuodemo.backend.service.DogService;
import constant.RabbitConstants;
import dto.Dog;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DogListener {
    private DogService dogService;
    private RabbitTemplate template;
    private RabbitConstants constants;

    @RabbitListener(queues = "${rabbit.queue.save}")
    public void onMessage(Dog dog) {
        List<Dog> saveDogs = dogService.saveDog(dog);
        template.convertAndSend(constants.getExchange(),constants.getRoutingKey().getSaveAnswer(),saveDogs);
    }
    @RabbitListener(queues = "${rabbit.queue.show}")
    public void onMessage(String message) {
        template.convertAndSend(constants.getExchange(), constants.getRoutingKey().getShowAnswer(), dogService.getDogs());
    }
}
