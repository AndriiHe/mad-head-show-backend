package com.incamp.mhs.team;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void save(Team team) {
        teamRepository.persist(team);
    }

    @Transactional(readOnly = true)
    public Team getById(Long id) {
        return teamRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Team> getAllTeams() {
        TeamSpecification teamSpecification = new TeamSpecification();
        return teamRepository.findBy(teamSpecification);
    }
}
