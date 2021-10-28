package com.example.frontend.service;

import constant.RabbitConstants;
import dto.Dog;
import lombok.AllArgsConstructor;
import mapper.DogMapper;

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
        return mapper
                .dogsFromMessage(
                        template.receive(
                                constants.getQueue().getShowAnswer(),
                                6000).getBody()
                );
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
