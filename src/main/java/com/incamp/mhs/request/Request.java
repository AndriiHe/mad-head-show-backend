package com.incamp.mhs.request;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.Game;
import com.incamp.mhs.team.Team;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Long id;

    @JsonView(MinimalView.class)
    @Column(name = "captain_name")
    private String captainName;

    @JsonView(MinimalView.class)
    private String phone;

    @JsonView(MinimalView.class)
    @Column(name = "team_size")
    private Integer teamSize;

    @JsonView(MinimalView.class)
    private Date date;

    @JsonView(WithTeam.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @JsonView(WithGame.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    public interface MinimalView {}

    public interface WithGame extends MinimalView, Game.MinimalView {}

    public interface WithTeam extends MinimalView, Team.MinimalView {}
}
