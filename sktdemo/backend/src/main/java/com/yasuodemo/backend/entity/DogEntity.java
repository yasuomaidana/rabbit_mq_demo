package com.yasuodemo.backend.entity;

import dto.DogDto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Dogs")
@Data
public class DogEntity{
    @Id
    @Column(name="iddogs")
    private int id;
    private String name;
    private String race;
    private byte age;
}
