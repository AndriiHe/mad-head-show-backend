package com.incamp.mhs.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incamp.mhs.game.Game;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class RequestForm {

    @NotBlank
    private Long gameId;

    @NotBlank
    private String captainName;

    @NotBlank
    private String phone;

    @NotBlank
    private Integer teamSize;

    @NotBlank
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;

    public Request toRequest() {
        Request request = new Request();

        Game game = new Game();
        game.setId(gameId);

        request.setCaptainName(captainName);
        request.setPhone(phone);
        request.setTeamSize(teamSize);
        request.setDate(date);
        request.setGame(game);

        return request;
    }
}


