package com.incamp.mhs.team;

import com.incamp.mhs.EntitySpecification;
import com.incamp.mhs.game.Game;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Setter
public class TeamSpecification implements EntitySpecification<Team> {


    @Override
    public CriteriaQuery<Team> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Team> teamCriteriaQuery = cb.createQuery(Team.class);
        Root<Team> teamRoot = teamCriteriaQuery.from(Team.class);
        teamCriteriaQuery.select(teamRoot);

        return teamCriteriaQuery;
    }
}

