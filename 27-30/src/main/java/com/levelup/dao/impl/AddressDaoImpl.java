package com.levelup.dao.impl;

import com.levelup.core.entity.Address;
import com.levelup.dao.AddressDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDaoImpl extends AbstractHibernateDao<Address> implements AddressDao {


    @Override
    public Address getOneById(long id) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Address.class, "address").add(Restrictions.eq("id", id));
        Address address = (Address)criteria.uniqueResult();
        session.close();
        return address;
    }

    @Override
    public List<Address> getAll() {
        Session session = sessionFactory.openSession();
        List<Address> list = session.createQuery("from Address").list();
        session.close();
        return list;    }
}
