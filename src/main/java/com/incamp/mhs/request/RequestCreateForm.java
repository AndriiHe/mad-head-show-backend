package com.incamp.mhs.request;

import com.incamp.mhs.game.Game;
import com.incamp.mhs.team.Team;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class RequestCreateForm {

    @NotBlank
    private Long gameId;

    @NotBlank
    private String captainName;

    @NotBlank
    private String phone;

    @NotBlank
    private Integer teamSize;

    @NotBlank
    private String teamName;

    public Request toRequest() {
        Request request = new Request();

        Game game = new Game();
        game.setId(gameId);

        Team team = new Team();
        team.setName(teamName);
        request.setTeam(team);

        request.setRequestStatus(RequestStatus.UNCONFIRMED);
        request.setCaptainName(captainName);
        request.setPhone(phone);
        request.setTeamSize(teamSize);
        request.setDate(LocalDate.now());
        request.setGame(game);

        return request;
    }
}


