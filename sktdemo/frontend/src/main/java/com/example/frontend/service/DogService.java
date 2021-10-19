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
        ArrayList<LinkedHashMap> rawInfo = (ArrayList<LinkedHashMap>) template.receiveAndConvert(constants.showAnswerQueue, 6000);
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

    public void saveDog(DogDto dog){
        template.convertAndSend(constants.exchange,constants.saveRoutingKey,dog);
        getDogs();
    }
}
