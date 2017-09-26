package com.incamp.mhs;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.beans.FeatureDescriptor;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class BaseRepository<Entity, Pk> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<Entity> entityClass;

    public BaseRepository() {
        entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void remove(Entity entity) {
        entityManager.remove(entity);
    }

    public void persist(Entity entity) {
        entityManager.persist(entity);
    }

    public void update(Pk pk, Entity entity) {
        Entity entityById = findOneByPk(pk).orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(entity, entityById, getNullOrZeroLengthPropertyNames(entity));
        persist(entityById);
    }

    public List<Entity> findBy(EntitySpecification<Entity> specification) {
        CriteriaQuery<Entity> userCriteriaQuery = specification.toCriteria(entityManager.getCriteriaBuilder());
        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    @Fetch(FetchMode.SELECT)
    public Optional<Entity> findOneByPk(Pk primaryKey) {
        return Optional.ofNullable(entityManager.find(entityClass, primaryKey));
    }

    private String[] getNullOrZeroLengthPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> {
                    Object sourcePropertyValue = wrappedSource.getPropertyValue(propertyName);
                    return sourcePropertyValue == null ||
                            Collection.class.isAssignableFrom(sourcePropertyValue.getClass()) &&
                                    Collection.class.cast(sourcePropertyValue).size() == 0;

                })
                .toArray(String[]::new);
    }
}
