package com.incamp.mhs.round.type;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class RoundTypeService {

    private final RoundTypeRepository roundTypeRepository;

    public RoundTypeService(RoundTypeRepository roundTypeRepository) {
        this.roundTypeRepository = roundTypeRepository;
    }

    @Transactional
    public void create(RoundType roundType) {
        roundTypeRepository.persist(roundType);
    }

    @Transactional(readOnly = true)
    public RoundType getById(Long id) {
        return roundTypeRepository.findOneByPk(id).orElseThrow(() -> new EntityNotFoundException("Round type not find"));
    }
}
