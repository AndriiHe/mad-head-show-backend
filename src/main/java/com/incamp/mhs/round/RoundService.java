package com.incamp.mhs.round;

import com.incamp.mhs.game.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoundService {

    private final RoundRepository roundRepository;

    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    @Transactional
    public void save(Long gameId, Round round) {
        Game game = new Game();
        game.setId(gameId);
        round.setGame(game);
        roundRepository.persist(round);
    }

    @Transactional(readOnly = true)
    public Round getById(Long id) {
        return roundRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Round> getByIndex(Long gameId, Integer index) {
        RoundSpecification roundSpecification = new RoundSpecification();
        roundSpecification.setOIndex(Optional.of(index));
        roundSpecification.setOGameId(Optional.of(gameId));
        return roundRepository.findBy(roundSpecification);
    }

    @Transactional(readOnly = true)
    public List<Round> getByGameId(Long gameId) {
        RoundSpecification roundSpecification = new RoundSpecification();
        roundSpecification.setOGameId(Optional.of(gameId));
        return roundRepository.findBy(roundSpecification);
    }
}
