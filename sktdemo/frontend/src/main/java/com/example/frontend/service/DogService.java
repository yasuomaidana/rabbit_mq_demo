package com.example.frontend.service;

import constant.RabbitConstants;
import dto.Dog;
import lombok.AllArgsConstructor;
import mapper.DogMapper;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DogService {

    private RabbitTemplate template;
    private RabbitConstants constants;
    private DogMapper mapper;

    public List<Dog> getDogs(){
        template.convertAndSend(constants.getExchange(),constants.getRoutingKey().getShow(),"Hi");
        Message receivedDogs = template.receive(constants.getQueue().getShowAnswer(),
                6000);
        if (receivedDogs==null) throw new AmqpException("Dogs couldn't be received");
        return mapper
                .dogsFromMessage(receivedDogs.getBody());
    }

    public List<Dog> saveDog(Dog dog){
        template.convertAndSend(constants.getExchange(),constants.getRoutingKey().getSave(),dog);
        return mapper
                .dogsFromMessage(
                        template.receive(
                                constants.getQueue().getSaveAnswer(),
                                6000).getBody());

    }
}
