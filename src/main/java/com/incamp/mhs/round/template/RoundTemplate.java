package com.incamp.mhs.round.template;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.template.GameTemplate;
import com.incamp.mhs.round.type.RoundType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@Table(name = "round_templates")
public class RoundTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(MinimalView.class)
    @Column(nullable = false)
    private String name;

    @JsonView(MinimalView.class)
    @NotBlank
    @Column(name = "number_of_quizzes", nullable = false)
    private Integer numberOfQuizzes;

    @JsonView(MinimalView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_type", nullable = false)
    @NotBlank
    private RoundType roundType;

    @JsonView(MinimalView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_template")
    private GameTemplate gameTemplate;

    public interface MinimalView {}
}

