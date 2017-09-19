package com.incamp.mhs.score.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class QuizScoreService {

    private final QuizScoreRepository quizScoreRepository;

    @Autowired
    public QuizScoreService(QuizScoreRepository quizScoreRepository) {
        this.quizScoreRepository = quizScoreRepository;
    }

    @Transactional
    public void save(QuizScore quizScore) {
        quizScoreRepository.persist(quizScore);
    }

    @Transactional(readOnly = true)
    public QuizScore getById(Long id) {
        return quizScoreRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<QuizScore> getByRoundIndexAndQuizIndex(Long gameId, Integer roundIndex, Integer quizIndex) {
        QuizScoreSpecification quizScoreSpecification = new QuizScoreSpecification();
        quizScoreSpecification.setOGame(Optional.of(gameId));
        quizScoreSpecification.setORoundIndex(Optional.of(roundIndex));
        quizScoreSpecification.setOQuizIndex(Optional.of(quizIndex));
        return quizScoreRepository.findBy(quizScoreSpecification);
    }

    @Transactional(readOnly = true)
    public List<QuizScore> getByRoundIndex(Long gameId, Integer roundIndex) {
        QuizScoreSpecification quizScoreSpecification = new QuizScoreSpecification();
        quizScoreSpecification.setOGame(Optional.of(gameId));
        quizScoreSpecification.setORoundIndex(Optional.of(roundIndex));

        return quizScoreRepository.findBy(quizScoreSpecification);
    }

    @Transactional(readOnly = true)
    public List<QuizScore> getByGameId(Long gameId) {
        QuizScoreSpecification quizScoreSpecification = new QuizScoreSpecification();
        quizScoreSpecification.setOGame(Optional.of(gameId));

        return quizScoreRepository.findBy(quizScoreSpecification);
    }

    @Transactional(readOnly = true)
    public List<QuizScore> getByTeamId(Long gameId, Long teamId) {
        QuizScoreSpecification quizScoreSpecification = new QuizScoreSpecification();
        quizScoreSpecification.setOGame(Optional.of(gameId));
        quizScoreSpecification.setOTeam(Optional.of(teamId));

        return quizScoreRepository.findBy(quizScoreSpecification);
    }

}
