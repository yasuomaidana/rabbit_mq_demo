package com.yasuodemo.backend.controller;

import com.yasuodemo.backend.config.MQConfig;
import dto.DogDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DogListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listenNewDog(DogDto dog){
         System.out.println("Preparing to save : "+dog);
    }
}
