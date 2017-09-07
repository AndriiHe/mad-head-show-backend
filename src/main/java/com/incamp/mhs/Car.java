package com.incamp.mhs;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Car {

    @JsonView(MinimalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(MinimalView.class)
    private String model;

    @JsonView(MinimalView.class)
    private Integer engineCapacity;

    public interface MinimalView{}
}
