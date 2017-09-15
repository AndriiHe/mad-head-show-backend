package com.incamp.mhs.score.game;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class GameScoreService {

    private final GameScoreRepository gameScoreRepository;

    public GameScoreService(GameScoreRepository gameScoreRepository) {
        this.gameScoreRepository = gameScoreRepository;
    }

    @Transactional
    public void create(GameScore gameScore) {
        gameScoreRepository.persist(gameScore);
    }

    @Transactional(readOnly = true)
    public GameScore getById(Long id) {
        return gameScoreRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}
