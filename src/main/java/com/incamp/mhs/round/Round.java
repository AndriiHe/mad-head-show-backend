package com.incamp.mhs.round;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.Game;
import com.incamp.mhs.round.type.RoundType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rounds")
public class Round {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(MinimalView.class)
    private Integer index;

    @JsonView(MinimalView.class)
    private String name;

    @JsonView(MinimalView.class)
    private Integer numberOfQuizzes;

    @JsonView(MinimalView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private RoundType roundType;

    @JsonView(GameView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    public interface MinimalView {}

    public interface GameView extends MinimalView {}
}
