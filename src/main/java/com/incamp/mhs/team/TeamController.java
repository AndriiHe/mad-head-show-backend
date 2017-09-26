package com.incamp.mhs.team;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@Transactional
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("{id}")
    @JsonView(Team.WithRequests.class)
    public Team getById(@PathVariable long id) {
        return teamService.getById(id);
    }

    @GetMapping
    @JsonView(Team.MinimalView.class)
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public void createTeam(@RequestBody @Valid TeamForm teamForm) {
        teamService.save(teamForm.toTeam());
    }
}
