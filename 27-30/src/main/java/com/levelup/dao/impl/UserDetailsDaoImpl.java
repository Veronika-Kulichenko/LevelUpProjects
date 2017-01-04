package com.levelup.dao.impl;

import com.levelup.core.entity.Address;
import com.levelup.core.entity.User;
import com.levelup.core.entity.UserDetails;
import com.levelup.dao.UserDao;
import com.levelup.dao.UserDetailsDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDetailsDaoImpl extends AbstractHibernateDao<UserDetails> implements UserDetailsDao {

    @Override
    public UserDetails getOneById(long id) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDetails.class, "userDetails").add(Restrictions.eq("id", id));
        UserDetails userDetails = (UserDetails)criteria.uniqueResult();
        session.close();
        return userDetails;
    }

    @Override
    public List<UserDetails> getAll() {
        Session session = sessionFactory.openSession();
        List<UserDetails> list = session.createQuery("from UserDetails").list();
        session.close();
        return list;
    }
}
