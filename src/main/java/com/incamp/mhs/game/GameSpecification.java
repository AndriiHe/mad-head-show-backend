package com.incamp.mhs.game;

import com.incamp.mhs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GameSpecification implements EntitySpecification<Game> {

    @Override
    public CriteriaQuery<Game> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Game> gameCriteriaQuery = cb.createQuery(Game.class);
        Root<Game> gameRoot = gameCriteriaQuery.from(Game.class);
        gameCriteriaQuery.select(gameRoot);
        return gameCriteriaQuery;
    }
}
