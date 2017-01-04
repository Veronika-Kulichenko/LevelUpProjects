package com.levelup.dao.impl;

import com.levelup.core.entity.User;
import com.levelup.dao.UserDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        Session session = sessionFactory.openSession();

        session.save(user);
        session.close();
    }

    public User getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where = :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();

//        Criteria criteria = session.createCriteria(User.class, "user").add(Restrictions.eq("email", email));
//        User user = (User)criteria.uniqueResult();
        session.close();
        return user;
    }


    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();

//        String email = (String) session.createCriteria(User.class).setProjection(Projections.property("email"), Projections.property("")).add(Restrictions.eq("id", id)).uniqueResult();
//
//        Query query = session.createQuery("SELECT new user(email, password) FROM user");
//        User u1 = (User) query.uniqueResult();


        Criteria criteria = session.createCriteria(User.class, "user").add(Restrictions.eq("id", id));
        User user = (User)criteria.uniqueResult();
        session.close();
        return user;
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();

        session.update(user);
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();

        session.delete(user);
        session.close();
    }

    @Override
    public boolean isPasswordCorrect(String login, String pass) {
        User user = getUserByEmail(login);
        return user != null && user.getPassword().equals(pass);
    }

}
