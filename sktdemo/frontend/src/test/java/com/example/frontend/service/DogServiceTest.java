package com.example.frontend.service;

import constant.RabbitConstants;
import dto.DogDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class DogServiceTest {
    @Mock
    private RabbitTemplate template;
    @Mock
    private RabbitConstants constants;
    @InjectMocks
    private DogService dogService;

    private ArrayList<LinkedHashMap> rawDogs;
    private DogDto dog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        rawDogs = new ArrayList<>();
        rawDogs.add(new LinkedHashMap());
        rawDogs.add(new LinkedHashMap());
        rawDogs.forEach(rawDog->{
            rawDog.put("name","terry");
            rawDog.put("race","bulldog");
            rawDog.put("age",3);
        });
        dog = DogDto.builder().name("Terry").race("Doverman").age((byte) 2).build();
    }

    @Test
    void getDogs() {
        doNothing().when(template).convertAndSend(constants.exchange,constants.showRoutingKey,"Hi");
        when(template.receiveAndConvert(constants.showAnswerQueue,6000)).thenReturn(rawDogs);
        assertNotNull(dogService.getDogs());
    }

    @Test
    void saveDog() {
        doNothing().when(template).convertAndSend(constants.exchange,constants.showRoutingKey,"Hi");
        when(template.receiveAndConvert(constants.showAnswerQueue,6000)).thenReturn(rawDogs);
        doNothing().when(template).convertAndSend(constants.exchange,constants.saveRoutingKey,dog);
        dogService.saveDog(dog);
    }

}