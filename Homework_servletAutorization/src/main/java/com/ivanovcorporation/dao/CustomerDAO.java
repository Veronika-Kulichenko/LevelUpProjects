package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Customer;

import java.util.List;

/**
 * Created by java on 20.02.2016.
 */
public interface CustomerDAO {

    Customer get(Long id);

    Long save(Customer customer);

    void delete(Customer customer);

    void update(Customer customer);

    List<Customer> list();
}
