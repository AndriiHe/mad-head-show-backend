package com.incamp.mhs.round.type;

import com.fasterxml.jackson.annotation.JsonView;
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

    @JsonView(MinimalView.class)
    private String name;

    @JsonView(MinimalView.class)
    private Double start;

    @JsonView(MinimalView.class)
    private Double step;

    public interface MinimalView {}
}
