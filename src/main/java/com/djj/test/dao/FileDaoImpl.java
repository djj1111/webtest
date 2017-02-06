package com.djj.test.dao;

import com.djj.test.entity.File;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */
public class FileDaoImpl implements FileDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public File getFile(int id) {
        String hql = "from File f where f.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, id);

        return (File) query.uniqueResult();
    }

    @Override
    public List<File> getAllFile() {
        String hql = "from File";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        return query.list();
    }

    @Override
    public void addFile(File file) {
        sessionFactory.getCurrentSession().save(file);
    }

    @Override
    public boolean delFile(int id) {
        String hql = "delete File f where f.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, id);

        return (query.executeUpdate() > 0);
    }

    @Override
    public boolean updateFile(File file) {
        String hql = "update File f set f.mid = ?,f.path=? where f.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, file.getMid());
        query.setString(1, file.getPath());
        query.setInteger(2, file.getId());

        return (query.executeUpdate() > 0);
    }
}
