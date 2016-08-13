package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Account;

import java.util.List;

/**
 * Created by java on 20.02.2016.
 */
public interface AccountDAO {

    Account get(Long id);

    Long save(Account account);

    void delete(Account account);

    void update(Account account);

    void deleteByCustomer(Long customerId);

    List<Account> list();

    List<Account> listOfAccountsByCustomer(Long customerId);
}
