package com.djj.test.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by djj on 2017/2/14.
 */
@Component
public class AbstractDaoImpl<PK extends Serializable, T> {

    private final Class<T> persistentClass;
    @Resource
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public AbstractDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    @SuppressWarnings("unchecked")
    public void delByKey(PK key) {
        T t = getByKey(key);
        getSession().delete(t);
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public Serializable save(T entity) {
        return getSession().save(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }
}
