package com.incamp.mhs.season;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class SeasonService {

    private final SeasonRepository seasonRepository;

    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Transactional
    public void save(Season season) {
        seasonRepository.persist(season);
    }

    @Transactional(readOnly = true)
    public Season getById(Long id) {
        return seasonRepository.findOneByPk(id).orElseThrow(() -> new EntityNotFoundException("Season not found"));
    }
}
