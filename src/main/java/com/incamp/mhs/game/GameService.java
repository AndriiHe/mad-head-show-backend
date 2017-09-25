package com.incamp.mhs.game;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public void save(Game game) {
        gameRepository.persist(game);
    }

    @Transactional
    public void update(Long id, Game game) {
        gameRepository.update(id, game);
    }

    @Transactional(readOnly = true)
    public Game getById(Long id) {
        return gameRepository.findOneByPk(id).orElseThrow(() -> new EntityNotFoundException("Game not found"));
    }

    @Transactional(readOnly = true)
    public List<Game> findBy(GameSpecification gameSpecification) {
        return gameRepository.findBy(gameSpecification);
    }

    @Transactional(readOnly = true)
    public List<Game> findByLocation(String location) {
        GameSpecification gameSpecification = new GameSpecification();
        gameSpecification.setOLocation(Optional.of(location));
        return gameRepository.findBy(gameSpecification);
    }

    @Transactional(readOnly = true)
    public List<Game> findByGameStatus(GameStatus gameStatus) {
        GameSpecification gameSpecification = new GameSpecification();
        gameSpecification.setOGameStatus(Optional.of(gameStatus));
        return gameRepository.findBy(gameSpecification);
    }
}