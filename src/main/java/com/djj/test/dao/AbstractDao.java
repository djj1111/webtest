package com.djj.test.dao;

import org.hibernate.Criteria;

import java.io.Serializable;

/**
 * Created by djj on 2017/2/14.
 */

/*
 *@param PK 主键
 *  persist()与save()的区别，save立即执行sql，persist可能flush()时批量执行
 *  ParameterizedType 参数类型数组
 *  getGenericSuperclass()返回父类类型 如com.djj.test.dao.AbstractDao<java.lang.Integer,com.djj.test.entity.File>
 */


public abstract interface AbstractDao<PK extends Serializable, T> {
    public T getByKey(PK key);

    public void delByKey(PK key);

    public void persist(T entity);

    public Serializable save(T entity);

    public void update(T entity);

    public void delete(T entity);

    public Criteria createEntityCriteria();
}


