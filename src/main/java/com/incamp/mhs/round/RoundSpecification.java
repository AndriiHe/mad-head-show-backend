package com.incamp.mhs.round;

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
public class RoundSpecification implements EntitySpecification<Round> {

    private Optional<Integer> oIndex = Optional.empty();
    private Optional<Long> oGameId = Optional.empty();


    @Override
    public CriteriaQuery<Round> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Round> roundCriteriaQuery = cb.createQuery(Round.class);
        Root<Round> roundRoot = roundCriteriaQuery.from(Round.class);
        roundCriteriaQuery.select(roundRoot);

        List<Predicate> predicateList = new ArrayList<>();

        oIndex.ifPresent((index) -> predicateList.add(cb.equal(roundRoot.get("index"), index)));
        oGameId.ifPresent((gameId) -> predicateList.add(cb.equal(roundRoot.get("game"), gameId)));
        Predicate mainPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        return roundCriteriaQuery.where(mainPredicate);
    }
}
