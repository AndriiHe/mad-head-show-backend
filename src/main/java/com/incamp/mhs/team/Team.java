package com.incamp.mhs.team;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.Game;
import com.incamp.mhs.request.Request;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Long id;

    @JsonView(MinimalView.class)
    @Column(nullable = false)
    private String name;

    @JsonView(WithGames.class)
    @ManyToMany(mappedBy = "teams", fetch = FetchType.LAZY)
    private Collection<Game> games = Collections.emptyList();

    @JsonView(WithRequests.class)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team", cascade = CascadeType.ALL)
    private Collection<Request> requests = Collections.emptyList();

    public interface MinimalView {}

    public interface WithGames extends MinimalView, Game.MinimalView {}

    public interface WithRequests extends MinimalView, Request.MinimalView {}

}
