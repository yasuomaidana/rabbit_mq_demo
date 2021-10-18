package com.example.frontend.service;

import com.example.frontend.config.WebConfig;
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

    public List<DogDto> getDogs(){
        ArrayList<LinkedHashMap> rawInfo = (ArrayList<LinkedHashMap>) template.receiveAndConvert(WebConfig.SHOW_ANSWER_QUEUE, 6000);
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
        template.convertAndSend(WebConfig.EXCHANGE,WebConfig.SAVE_ROUTING_KEY,dog);
    }
}
