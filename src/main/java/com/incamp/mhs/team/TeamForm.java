package com.incamp.mhs.team;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TeamForm {
    @NotBlank
    String name;

    public Team toTeam(){
        Team team = new Team();
        team.setName(name);
        return team;
    }
}
