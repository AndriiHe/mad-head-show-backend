package com.incamp.mhs.game;

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
public class GameSpecification implements EntitySpecification<Game> {

    private Optional<String> oLocation = Optional.empty();
    private Optional<GameStatus> oGameStatus = Optional.empty();

    @Override
    public CriteriaQuery<Game> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Game> gameCriteriaQuery = cb.createQuery(Game.class);
        Root<Game> gameRoot = gameCriteriaQuery.from(Game.class);
        gameCriteriaQuery.select(gameRoot);

        List<Predicate> predicateList = new ArrayList<>();

        oLocation.ifPresent((location) -> predicateList.add(cb.equal(gameRoot.get("location"), location)));
        oGameStatus.ifPresent((gameStatus) -> predicateList.add(cb.equal(gameRoot.get("gameStatus"), gameStatus)));
        Predicate mainPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        return gameCriteriaQuery.where(mainPredicate);
    }
}
