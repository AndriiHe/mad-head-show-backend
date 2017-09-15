package com.incamp.mhs.game;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public void create(Game game) {
        gameRepository.persist(game);
    }

    @Transactional(readOnly = true)
    public Game getById(Long id) {
        return gameRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}