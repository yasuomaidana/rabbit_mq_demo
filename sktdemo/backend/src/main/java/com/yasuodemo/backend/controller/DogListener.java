package com.yasuodemo.backend.controller;

import com.yasuodemo.backend.config.MQConfig;
import com.yasuodemo.backend.service.DogService;
import dto.DogDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DogListener {

    private RabbitTemplate template;
    private DogService dogService;

    @RabbitListener(queues = MQConfig.SAVE_QUEUE)
    public void listenNewDog(DogDto dog){
        dogService.saveDog(dog);
    }

    @RabbitListener(queues = MQConfig.SHOW_QUEUE)
    public void listenShowDog(String message){
        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.SHOW_ANSWER_ROUTING_KEY,dogService.getDogs());
    }
}
