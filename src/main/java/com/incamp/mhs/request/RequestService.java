package com.incamp.mhs.request;

import com.incamp.mhs.team.Team;
import com.incamp.mhs.team.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final TeamService teamService;

    public RequestService(RequestRepository requestRepository, TeamService teamService) {
        this.requestRepository = requestRepository;
        this.teamService = teamService;
    }

    @Transactional
    public void save(Request request) {
        List<Team> teamList = teamService.getByTeamName(request.getTeam().getName());
        for (Team team : teamList) {
            if (team.getId() != null) {
                request.setTeam(team);
            } else {
                teamService.save(team);
            }
        }

        requestRepository.persist(request);
    }

    @Transactional(readOnly = true)
    public Request getById(Long id) {
        return requestRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}