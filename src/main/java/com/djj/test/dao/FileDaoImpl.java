package com.djj.test.dao;

import com.djj.test.entity.File;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
        //query.setCacheable(true);
        query.setInteger(0, id);

        return (File) query.uniqueResult();
    }

    @Override
    public List<File> getAllFile() {
        String hql = "from File";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //设置查询缓存
        //query.setCacheable(true);
        //如果要自己指定查询缓存的name属性，一般使用query.YourCacheName 。也就是一般会使用query.开头。
        //如果不指定，默认的name是：org.hibernate.cache.StandardQueryCache，或者是org.hibernate.cache.UpdateTimestampsCache。
        //需要在ehcache.xml中定义
        //query.setCacheRegion("query.YourCacheName");
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
