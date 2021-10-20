package com.yasuodemo.backend.service;

import com.yasuodemo.backend.entity.DogEntity;
import com.yasuodemo.backend.repository.DogRepository;
import dto.DogDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DogService {
    private DogRepository repository;

    public List<DogDto> getDogs(){
        return converToList(repository.getDogs());
    }

    public List<DogDto> saveDog(DogDto dog){
        return converToList(repository.saveDog(dog.getName(), dog.getRace(),dog.getAge()));
    }
    private List<DogDto> converToList(List<DogEntity> rawDogs){
        return rawDogs.stream()
                .map(dogEntity ->
                        {
                            return DogDto.builder()
                                    .name(dogEntity.getName())
                                    .race(dogEntity.getRace())
                                    .age(dogEntity.getAge())
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }
}
