package com.incamp.mhs.game;

import com.incamp.mhs.season.SeasonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final SeasonService seasonService;

    public GameService(GameRepository gameRepository, SeasonService seasonService) {
        this.gameRepository = gameRepository;
        this.seasonService = seasonService;
    }

    @Transactional
    public void save(Game game) {
        if (Objects.nonNull(game.getSeason())) {
            seasonService.getById(game.getSeason().getId());
        }
        gameRepository.persist(game);
    }

    @Transactional(readOnly = true)
    public Game getById(Long id) {
        return gameRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
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