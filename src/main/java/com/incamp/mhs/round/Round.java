package com.incamp.mhs.round;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rounds")
public class Round {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
