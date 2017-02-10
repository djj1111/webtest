package com.djj.test.dao;

import com.djj.test.entity.BlobFile;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * Created by djj on 2017/2/10.
 */
public class BlobFileDaoImpl implements BlobFileDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BlobFile getBlobFile(int id) {
        String hql = "from BlobFile f where f.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //query.setCacheable(true);
        String s = "";
        query.setParameter(0, id);
        return (BlobFile) query.uniqueResult();
    }

    @Override
    public void addBlobFile(BlobFile blobFile) {
        sessionFactory.getCurrentSession().save(blobFile);
    }
}
