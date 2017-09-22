package com.incamp.mhs.team;

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
public class TeamSpecification implements EntitySpecification<Team> {

    public Optional<String> oName = Optional.empty();

    @Override
    public CriteriaQuery<Team> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Team> teamCriteriaQuery = cb.createQuery(Team.class);
        Root<Team> teamRoot = teamCriteriaQuery.from(Team.class);
        teamCriteriaQuery.select(teamRoot);

        List<Predicate> predicateList = new ArrayList<>();

        oName.ifPresent(name -> predicateList.add(cb.equal(teamRoot.get("name"), name)));
        Predicate mainPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        return teamCriteriaQuery.where(mainPredicate);
    }
}

