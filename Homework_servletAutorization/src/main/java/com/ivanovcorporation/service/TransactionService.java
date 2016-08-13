package com.ivanovcorporation.service;


import com.ivanovcorporation.dao.TransactionDAO;
import com.ivanovcorporation.dao.TransactionDAOImpl;
import com.ivanovcorporation.domain.Transaction;

import java.util.List;

/**
 * Created by Sergey on 22.02.2016.
 */
public class TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAOImpl();

    public Transaction get(Long id) {
        return transactionDAO.get(id);
    }

    public Long save(Transaction transaction) {

        return transactionDAO.save(transaction);
    }

    public void update(Transaction transaction){
        transactionDAO.update(transaction);
    }

    public void delete(Transaction transaction) {
        transactionDAO.delete(transaction);
    }

    public void deleteByAccount(Long accountId){

        transactionDAO.deleteByAccount(accountId);
    }

    public List<Transaction> list() {
        return transactionDAO.list();
    }

    public List<Transaction> listOfTransactionsByAccount(Long accountnumber) {
        return transactionDAO.listOfTransactionsByAccount(accountnumber);
    }
}
