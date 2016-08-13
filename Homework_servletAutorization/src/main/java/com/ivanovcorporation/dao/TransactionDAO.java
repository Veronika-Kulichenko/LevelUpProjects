package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Transaction;

import java.util.List;

/**
 * Created by java on 20.02.2016.
 */
public interface TransactionDAO {

    Transaction get(Long id);

    Long save(Transaction transaction);

    void update(Transaction transaction);

    void delete(Transaction transaction);

    void deleteByAccount(Long accountId);

    List<Transaction> list();

    List<Transaction> listOfTransactionsByAccount(Long accountnumber);
}
