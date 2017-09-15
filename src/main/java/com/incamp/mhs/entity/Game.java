package com.incamp.mhs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;

    private String location;

    @JsonFormat(pattern = "HH:mm")
    private Date time;

    //ToDo Add fields it!!!
}
