package com.example.frontend.service;

import constant.RabbitConstants;
import dto.DogDto;
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

    public List<DogDto> getDogs(){
        template.convertAndSend(constants.exchange,constants.showRoutingKey,"Hi");
        return  rawToList(template.receiveAndConvert(constants.showAnswerQueue, 6000));
    }

    public List<DogDto> saveDog(DogDto dog){
        template.convertAndSend(constants.exchange,constants.saveRoutingKey,dog);
        Object recMessage = template.receiveAndConvert(constants.saveAnswerQueue, 6000);
        while(recMessage==null){
        }
        return rawToList(recMessage);
    }
    private List<DogDto> rawToList(Object rawResponse){
        ArrayList<LinkedHashMap> rawInfo = (ArrayList<LinkedHashMap>) rawResponse;
        return  rawInfo.stream()
                .map(rawDog->{
                    Integer age = (Integer) rawDog.get("age");
                    return DogDto.builder()
                            .name((String) rawDog.get("name"))
                            .race((String) rawDog.get("race"))
                            .age(age.byteValue())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
