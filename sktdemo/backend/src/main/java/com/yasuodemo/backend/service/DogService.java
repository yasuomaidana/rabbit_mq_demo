package com.yasuodemo.backend.service;

import com.yasuodemo.backend.entity.DogEntity;
import com.yasuodemo.backend.repository.DogRepository;
import dto.Dog;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DogService {
    private DogRepository repository;
    private RabbitTemplate template;
    public List<Dog> getDogs(){
        return converToList(repository.getDogs());
    }

    public List<Dog> saveDog(Dog dog){
        return converToList(repository.saveDog(dog.getName(), dog.getRace(),dog.getAge()));
    }
    //add mapper
    private List<Dog> converToList(List<DogEntity> rawDogs){
        return rawDogs.stream()
                .map(dogEntity ->
                        {
                            return Dog.builder()
                                    .name(dogEntity.getName())
                                    .race(dogEntity.getRace())
                                    .age(dogEntity.getAge())
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }
}
