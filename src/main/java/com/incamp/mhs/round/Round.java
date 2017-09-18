package com.incamp.mhs.round;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.Game;
import com.incamp.mhs.round.type.RoundType;
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
    @JsonView(MinimalView.class)
    private Long id;

    @JsonView(MinimalView.class)
    @Column(nullable = false)
    private Integer index;

    @JsonView(MinimalView.class)
    @Column(nullable = false)
    private String name;

    @JsonView(MinimalView.class)
    @Column(name = "number_of_quizzes", nullable = false)
    private Integer numberOfQuizzes;

    @JsonView(MinimalView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_type", nullable = false)
    private RoundType roundType;

    @JsonView(GameView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Game game;

    public interface MinimalView {}

    public interface GameView extends MinimalView {}
}
