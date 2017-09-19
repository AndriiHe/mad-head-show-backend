package com.incamp.mhs.game;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incamp.mhs.season.Season;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class GameForm {

    @NotBlank
    private String location;

    @NotBlank
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;

    @NotBlank
    @JsonFormat(pattern = "HH:mm")
    private Date time;

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
