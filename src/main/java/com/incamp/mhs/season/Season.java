package com.incamp.mhs.season;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.game.Game;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Long id;

    @JsonView(MinimalView.class)
    private String name;

    @JsonView(WithGames.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "season", fetch = FetchType.LAZY)
    private Collection<Game> games = Collections.emptyList();

    public interface MinimalView {}

    public interface WithGames extends MinimalView, Game.MinimalView {}
}