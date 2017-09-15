package com.incamp.mhs.round.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "round_types")
public class RoundType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
