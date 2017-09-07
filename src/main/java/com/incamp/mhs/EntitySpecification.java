package com.incamp.mhs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface EntitySpecification<Entity> {
    CriteriaQuery<Entity> toCriteria(CriteriaBuilder cb);
}
