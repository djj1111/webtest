package com.djj.test.dao;

import com.djj.test.entity.BlobFile;
import org.springframework.stereotype.Component;

/**
 * Created by djj on 2017/2/10.
 */

@Component
public class BlobFileDaoImpl extends AbstractDao<Integer, BlobFile> implements BlobFileDao {

    /* @Resource
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BlobFile getBlobFile(int id) {
        //用了lazy,不能用load,一定要用hql
        *//*String hql = "from BlobFile f where f.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);*//*
        //查询cache对hql不起作用
        //query.setCacheable(true);
        //String s = "";
       *//* query.setParameter(0, id);
        return (BlobFile) query.uniqueResult();*//*
        return (BlobFile) sessionFactory.getCurrentSession().load(BlobFile.class, id);
    }

    @Override
    public String getFileName(int id) {
        return ((BlobFile) sessionFactory.getCurrentSession().load(BlobFile.class, id)).getText();
    }

    @Override
    public void addBlobFile(BlobFile blobFile) {
        sessionFactory.getCurrentSession().save(blobFile);
    }*/
}
