package com.yasuodemo.backend.service;

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
        return repository.getDogs().stream()
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

    public void saveDog(DogDto dog){
        repository.saveDog(dog.getName(), dog.getRace(),dog.getAge());
    }
}
