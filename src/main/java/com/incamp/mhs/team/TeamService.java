package com.incamp.mhs.team;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void create(Team team) {
        teamRepository.persist(team);
    }

    @Transactional(readOnly = true)
    public Team getById(Long id) {
        return teamRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}
