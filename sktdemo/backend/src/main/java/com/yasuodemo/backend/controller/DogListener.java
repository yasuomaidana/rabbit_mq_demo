package com.yasuodemo.backend.controller;

import com.yasuodemo.backend.config.MQConfig;
import com.yasuodemo.backend.service.DogService;
import constant.RabbitConstants;
import dto.DogDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DogListener {

    private RabbitTemplate template;
    private DogService dogService;
    private RabbitConstants constants;


    public void listenNewDog(DogDto dog){
        dogService.saveDog(dog);
    }

    @RabbitListener(queues = MQConfig.SHOW_QUEUE)
    public void listenShowDog(String message){
        template.convertAndSend(constants.exchange,MQConfig.SHOW_ANSWER_ROUTING_KEY,dogService.getDogs());
    }
}
