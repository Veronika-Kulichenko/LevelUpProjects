package com.levelup.dao.impl;

import com.levelup.dao.HibernateDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexandr Shegeda on 30.09.16.
 */
@Repository
public abstract class AbstractHibernateDao<T> implements HibernateDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void save(T t) {
        Session session = sessionFactory.openSession();

        session.save(t);
        session.close();
    }

    @Override
    public void saveAll(List<T> list) {
        for(T t: list){
            save(t);
        }
    }

    @Override
    public void delete(T t) {
        Session session = sessionFactory.openSession();

        session.delete(t);
        session.close();
    }

    @Override
    public void deleteAll(List<T> list) {
        for(T t: list){
            delete(t);
        }
    }

    @Override
    public void update(T t) {
        Session session = sessionFactory.openSession();

        session.update(t);
        session.close();
    }

    @Override
    public void updateAll(List<T> list) {
        for(T t: list){
            update(t);
        }
    }

//    @Override
//    public T getOneById(long id) {
//        return null;
//    }

//    @Override
//    public List<T> getAll() {
//        Session session = sessionFactory.openSession();
//        session.createQuery(String.format("from %", )).list();
//        return ;
//    }
}
