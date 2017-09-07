package com.incamp.mhs.quiz_score;

import com.incamp.mhs.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class QuizScoreRepository extends BaseRepository<QuizScore,Integer> {

    private EntityManager entityManager;

    public QuizScoreRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
