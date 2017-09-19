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
    public void save(GameForm gameForm) {
        Game gameFromDto = gameForm.toGame();
        Game game;
        if (Objects.nonNull(gameFromDto.getId())) {
            game = copyGameFromDto(gameFromDto);
        } else {
            game = gameFromDto;
        }
        if (Objects.nonNull(game.getSeason())) {
            seasonService.getById(game.getSeason().getId());
        }
        gameRepository.persist(game);
    }

    private Game copyGameFromDto(Game gameFromDto) {
        Game game;
        game = getById(gameFromDto.getId());

        if (Objects.nonNull(gameFromDto.getLocation())) {
            game.setLocation(gameFromDto.getLocation());
        }

        if (Objects.nonNull(gameFromDto.getDate())) {
            game.setDate(gameFromDto.getDate());
        }

        if (Objects.nonNull(gameFromDto.getTime())) {
            game.setTime(gameFromDto.getTime());
        }

        if (Objects.nonNull(gameFromDto.getGameStatus())) {
            game.setGameStatus(gameFromDto.getGameStatus());
        }

        if (Objects.nonNull(gameFromDto.getCurrentQuiz())) {
            game.setCurrentQuiz(gameFromDto.getCurrentQuiz());
        }

        if (Objects.nonNull(gameFromDto.getCurrentRound())) {
            game.setCurrentRound(gameFromDto.getCurrentRound());
        }

        if (Objects.nonNull(gameFromDto.getSeason())) {
            game.setSeason(gameFromDto.getSeason());
        }
        return game;
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