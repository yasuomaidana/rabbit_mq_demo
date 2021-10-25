package com.example.frontend.service;

import constant.RabbitConstants;
import constant.RabbitQueuesConfigurationProperties;
import constant.RabbitRoutingConfiguration;
import dto.Dog;
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
    private Dog dog;
    private void initConstants(){
        constants.setExchange("fake-exchange");
        constants.setRoutingKey(new RabbitRoutingConfiguration());
        constants.setQueue(new RabbitQueuesConfigurationProperties());
        constants.getRoutingKey().setShow("fake-show-routing");
        constants.getQueue().setShowAnswer("fake-show-answer-queue");
        constants.getQueue().setShow("fake-show-queue");
        constants.getRoutingKey().setSave("fake-save-routing");
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        constants = new RabbitConstants();
        rawDogs = new ArrayList<>();
        rawDogs.add(new LinkedHashMap());
        rawDogs.add(new LinkedHashMap());
        rawDogs.forEach(rawDog->{
            rawDog.put("name","terry");
            rawDog.put("race","bulldog");
            rawDog.put("age",3);
        });
        dog = Dog.builder().name("Terry").race("Doberman").age((byte) 2).build();
        initConstants();
        dogService = new DogService(template,constants);
    }

    @Test
    void getDogs() {
        doNothing().when(template).convertAndSend(constants.getExchange(),constants.getRoutingKey().getShow(),"Hi");
        when(template.receiveAndConvert(constants.getQueue().getShowAnswer(),6000)).thenReturn(rawDogs);
        assertNotNull(dogService.getDogs());
    }

    @Test
    void saveDog() {
        doNothing().when(template).convertAndSend(constants.getExchange(),constants.getRoutingKey().getShow(),"Hi");
        when(template.receiveAndConvert(constants.getQueue().getSaveAnswer(),6000)).thenReturn(rawDogs);
        doNothing().when(template).convertAndSend(constants.getExchange(),constants.getRoutingKey().getSave(),dog);
        assertNotNull(dogService.saveDog(dog));
    }

}