package com.incamp.mhs;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.annotation.AnnotationUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;
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

    private <T> T findEager(Class<T> type, Object id) {
        T entity = entityManager.find(type, id);

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();

            for (Annotation annotation : annotations) {
                if (Objects.equals(annotation.annotationType(), ManyToOne.class)) {
                    Map<String, Object> annotationAttributes = AnnotationUtils.getAnnotationAttributes(annotation);
                    if (annotationAttributes.containsKey("fetch")) {
                        if (Objects.equals(annotationAttributes.get("fetch").toString(), "LAZY")) {
                            try {
                                new PropertyDescriptor(field.getName(), type).getReadMethod().invoke(entity);
                            } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return entity;
    }
}
