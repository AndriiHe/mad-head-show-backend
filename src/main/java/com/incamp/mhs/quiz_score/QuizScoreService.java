package com.incamp.mhs.quiz_score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizScoreService {

    private final QuizScoreRepository quizScoreRepository;

    @Autowired
    public QuizScoreService(QuizScoreRepository quizScoreRepository) {
        this.quizScoreRepository = quizScoreRepository;
    }

    public void create(QuizScore quizScore) {
        quizScoreRepository.persist(quizScore);
    }

    public QuizScore getById(Integer id) {
        return quizScoreRepository.findOneByPk(id).orElseThrow(RuntimeException::new);
    }
}
