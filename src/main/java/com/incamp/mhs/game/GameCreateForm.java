package com.incamp.mhs.game;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incamp.mhs.season.Season;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class GameCreateForm {

    @NotBlank
    private String location;

    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    private Long seasonId;

    public Game toGame() {
        Game game = new Game();
        game.setDate(date);
        game.setTime(time);
        game.setCurrentQuiz(1);
        game.setCurrentRound(1);
        game.setLocation(location);
        game.setGameStatus(GameStatus.OPEN);
        if (seasonId != null) {
            Season season = new Season();
            season.setId(seasonId);
            game.setSeason(season);
        }
        return game;
    }
}
