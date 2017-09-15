package com.incamp.mhs.game;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.score.game.GameScore;
import com.incamp.mhs.season.Season;
import com.incamp.mhs.team.Team;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Data
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Long id;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonView(MinimalView.class)
    private Date date;

    @JsonFormat(pattern = "HH:mm")
    @JsonView(MinimalView.class)
    private Date time;

    @JsonView(MinimalView.class)
    private String location;

    @JsonView(MinimalView.class)
    private Integer currentQuiz;

    @JsonView(MinimalView.class)
    private Integer currentRound;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Season season;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private GameScore gameScore;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "games_teams", joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
    private Collection<Team> teams = Collections.emptyList();

    public interface MinimalView {}
}
