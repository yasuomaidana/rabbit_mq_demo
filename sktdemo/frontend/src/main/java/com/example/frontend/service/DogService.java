package com.example.frontend.service;

import constant.RabbitConstants;
import dto.Dog;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DogService {

    private RabbitTemplate template;
    private RabbitConstants constants;

    public List<Dog> getDogs(){
        template.convertAndSend(constants.getExchange(),constants.getRoutingKey().getShow(),"Hi");
        return  rawToList(template.receiveAndConvert(constants.getQueue().getShowAnswer(), 6000));
    }

    public List<Dog> saveDog(Dog dog){
        template.convertAndSend(constants.getExchange(),constants.getRoutingKey().getSave(),dog);
        Object recMessage = template.receiveAndConvert(constants.getQueue().getSaveAnswer(), 6000);
        return rawToList(recMessage);
    }
    private List<Dog> rawToList(Object rawResponse){
        ArrayList<LinkedHashMap> rawInfo = (ArrayList<LinkedHashMap>) rawResponse;
        return  rawInfo.stream()
                .map(rawDog->{
                    Integer age = (Integer) rawDog.get("age");
                    return Dog.builder()
                            .name((String) rawDog.get("name"))
                            .race((String) rawDog.get("race"))
                            .age(age.byteValue())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
