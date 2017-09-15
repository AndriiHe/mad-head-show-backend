package com.incamp.mhs.score.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class QuizScoreService {

    private final QuizScoreRepository quizScoreRepository;

    @Autowired
    public QuizScoreService(QuizScoreRepository quizScoreRepository) {
        this.quizScoreRepository = quizScoreRepository;
    }

    @Transactional
    public void create(QuizScore quizScore) {
        quizScoreRepository.persist(quizScore);
    }

    @Transactional(readOnly = true)
    public QuizScore getById(Long id) {
        return quizScoreRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}
