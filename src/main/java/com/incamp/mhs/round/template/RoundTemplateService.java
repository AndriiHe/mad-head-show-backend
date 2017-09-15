package com.incamp.mhs.round.template;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class RoundTemplateService {

    private final RoundTemplateRepository roundTemplateRepository;

    public RoundTemplateService(RoundTemplateRepository roundTemplateRepository) {
        this.roundTemplateRepository = roundTemplateRepository;
    }

    @Transactional
    public void save(RoundTemplate roundTemplate) {
        roundTemplateRepository.persist(roundTemplate);
    }

    @Transactional(readOnly = true)
    public RoundTemplate getById(Long id) {
        return roundTemplateRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}
