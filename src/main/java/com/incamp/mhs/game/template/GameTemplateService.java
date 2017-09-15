package com.incamp.mhs.game.template;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class GameTemplateService {

    private final GameTemplateRepository gameTemplateRepository;

    public GameTemplateService(GameTemplateRepository gameTemplateRepository) {
        this.gameTemplateRepository = gameTemplateRepository;
    }

    @Transactional
    public void create(GameTemplate gameTemplate) {
        gameTemplateRepository.persist(gameTemplate);
    }

    @Transactional(readOnly = true)
    public GameTemplate getById(Long id) {
        return gameTemplateRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}
