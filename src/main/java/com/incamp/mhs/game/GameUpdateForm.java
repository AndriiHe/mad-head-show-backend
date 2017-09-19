package com.incamp.mhs.game;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incamp.mhs.season.Season;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Data
public class GameUpdateForm implements GameForm {

    private Long id;

    private String location;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    private GameStatus gameStatus;

    private Integer currentQuiz;

    private Integer currentRound;

    private Long seasonId;


    @Override
    public Game toGame() {
        Game game = new Game();

        game.setId(id);
        game.setLocation(location);
        game.setDate(date);
        game.setTime(time);
        game.setGameStatus(gameStatus);
        game.setCurrentQuiz(currentQuiz);
        game.setCurrentRound(currentRound);

        if (Objects.nonNull(seasonId)) {
            Season season = new Season();
            season.setId(seasonId);
            game.setSeason(season);
        }

        return game;
    }
}
