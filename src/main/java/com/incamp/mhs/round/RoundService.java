package com.incamp.mhs.round;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class RoundService {

    private final RoundRepository roundRepository;

    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    @Transactional
    public void save(Round round) {
        roundRepository.persist(round);
    }

    @Transactional(readOnly = true)
    public Round getById(Long id) {
        return roundRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}
