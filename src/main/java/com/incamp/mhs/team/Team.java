package com.incamp.mhs.team;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.Game;
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
    private String name;

    @ManyToMany(mappedBy = "teams")
    private Collection<Game> games = Collections.emptyList();

    public interface MinimalView {}
}
