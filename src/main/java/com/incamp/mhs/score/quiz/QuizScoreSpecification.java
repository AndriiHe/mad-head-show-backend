package com.incamp.mhs.score.quiz;

import com.incamp.mhs.EntitySpecification;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
public class QuizScoreSpecification implements EntitySpecification<QuizScore> {

    private Optional<Integer> oQuizIndex = Optional.empty();
    private Optional<Integer> oRoundIndex = Optional.empty();
    private Optional<Integer> oGame = Optional.empty();


    @Override
    public CriteriaQuery<QuizScore> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<QuizScore> quizScoreCriteriaQuery = cb.createQuery(QuizScore.class);
        Root<QuizScore> quizScoreRoot = quizScoreCriteriaQuery.from(QuizScore.class);
        quizScoreCriteriaQuery.select(quizScoreRoot);

        List<Predicate> predicateList = new ArrayList<>();

        oGame.ifPresent(gameId -> predicateList.add(cb.equal(quizScoreRoot.get("game"), gameId)));
        oQuizIndex.ifPresent(index -> predicateList.add(cb.equal(quizScoreRoot.get("quizIndex"), index)));
        oRoundIndex.ifPresent(roundIndex -> predicateList.add(cb.equal(quizScoreRoot.get("roundIndex"), roundIndex)));
        Predicate mainPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        return quizScoreCriteriaQuery.where(mainPredicate);
    }
}
