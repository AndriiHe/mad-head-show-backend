package com.incamp.mhs.score.quiz;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.Game;
import com.incamp.mhs.team.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quiz_scores")
public class QuizScore {

    @Id
    @JsonView(MinimalView.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(MinimalView.class)
    private Double score;

    @JsonView(MinimalView.class)
    private Integer quizIndex;

    @JsonView(MinimalView.class)
    private Integer roundIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView(MinimalView.class)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView(MinimalView.class)
    private Game game;

    public interface MinimalView extends Team.MinimalView, Game.MinimalView {
    }
}
