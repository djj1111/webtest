package com.djj.test.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by djj on 2017/2/14.
 */
//@Component
public class AbstractDaoImpl<PK extends Serializable, T> implements AbstractDao<PK, T> {

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
        /*try{
            Field f = entity.getClass().getDeclaredField("id");
            f.setAccessible(true);
            PK val =(PK)f.get(entity);

        }catch (NoSuchFieldException e){
            e.printStackTrace();
            System.out.println("Prim Key must named id");
            return false;
        }catch (IllegalAccessException e){
            e.printStackTrace();
            System.out.println("id are not accessible.");
            return false;
        }

        //设置些属性是可以访问的

        PK .get()*/
        return getSession().save(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }
}
