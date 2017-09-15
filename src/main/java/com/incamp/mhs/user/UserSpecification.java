package com.incamp.mhs.user;

import com.incamp.mhs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserSpecification implements EntitySpecification<User> {

    private Optional<String> oName = Optional.empty();

    public void setName(String name) {
        this.oName = Optional.of(name);
    }

    @Override
    public CriteriaQuery<User> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> userEntity = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userEntity);

        List<Predicate> predicateList = new ArrayList<>();

        oName.ifPresent(name -> predicateList.add(cb.equal(userEntity.get("name"), name)));
        Predicate mainPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        userCriteriaQuery.where(mainPredicate);

        return userCriteriaQuery;
    }
}
