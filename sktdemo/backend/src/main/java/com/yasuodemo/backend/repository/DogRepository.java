package com.yasuodemo.backend.repository;

import com.yasuodemo.backend.entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<DogEntity,Integer> {

    @Query(value ="{call getDogs()}",nativeQuery = true)
    List<DogEntity> getDogs();

    @Transactional
    @Modifying
    @Query(value="{call saveDog(:nameIn, :raceIn, :ageIN)}",nativeQuery = true)
    List<DogEntity> saveDog(
            @Param("nameIn") String nameIn,
            @Param("raceIn") String raceIn,
            @Param("ageIN") Byte ageIn);
}
