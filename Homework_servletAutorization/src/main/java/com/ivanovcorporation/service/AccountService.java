package com.ivanovcorporation.service;

import com.ivanovcorporation.dao.AccountDAO;
import com.ivanovcorporation.dao.AccountDAOImpl;
import com.ivanovcorporation.domain.Account;
import com.ivanovcorporation.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Sergey on 22.02.2016.
 */
public class AccountService {

    private AccountDAO accountDAO = new AccountDAOImpl();

    public Account get(Long id) {
        return accountDAO.get(id);
    }

    public Long save(Account account) {
        Long idOfInsertedAccount = 0L;
        //If the balance is 0 we will create an account but won't create a transaction
        if (account.getBalance().equals(new BigDecimal(0))) {

            idOfInsertedAccount = accountDAO.save(account);
            //otherwise we will create an account and transaction
            return  idOfInsertedAccount;
        } else {
            idOfInsertedAccount = accountDAO.save(account);
            TransactionService transactionService = new TransactionService();

            transactionService.save(new Transaction(new BigDecimal(100), "PUT", idOfInsertedAccount));
            return idOfInsertedAccount;
        }
    }

    public void delete(Account account) {
        TransactionService transactionService = new TransactionService();
        //Deleting transactions first
        transactionService.deleteByAccount(account.getAccountnumber());
        //Then deleting the account
        accountDAO.delete(account);
    }

    public void update(Account account) {

        accountDAO.update(account);
    }

    public List<Account> list() {
        return accountDAO.list();
    }

    public List<Account> listOfAccountsByCustomer(Long customerId) {
        return accountDAO.listOfAccountsByCustomer(customerId);
    }
}
