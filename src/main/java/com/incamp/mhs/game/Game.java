package com.incamp.mhs.game;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.round.Round;
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

    @JsonView()
    private Integer currentQuiz;

    @JsonView()
    private Integer currentRound;

    @JsonView(WithSeason.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @JsonView(WithTeams.class)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "games_teams", joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
    private Collection<Team> teams = Collections.emptyList();

    @JsonView(WithRounds.class)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.ALL)
    private Collection<Round> rounds = Collections.emptyList();

    public interface MinimalView {}

    public interface WithSeason extends MinimalView, Season.MinimalView {}

    public interface WithTeams extends MinimalView, Team.MinimalView {}

    public interface WithRounds extends MinimalView, Round.MinimalView {}
}
