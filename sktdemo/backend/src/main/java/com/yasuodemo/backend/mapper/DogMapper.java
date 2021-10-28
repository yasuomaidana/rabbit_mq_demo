package com.yasuodemo.backend.mapper;

import com.yasuodemo.backend.entity.DogEntity;
import dto.Dog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DogMapper extends mapper.DogMapper {

    public abstract Dog entityToDto(DogEntity dogEntity);
}
