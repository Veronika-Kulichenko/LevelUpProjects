package com.ivanovcorporation.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by java on 20.02.2016.
 */
public class Account {

    public Account(Long accountnumber, BigDecimal balance, Timestamp creationdate, String currency, Boolean blocked, Long customerid) {
        this.accountnumber = accountnumber;
        this.balance = balance;
        this.creationdate = creationdate;
        this.currency = currency;
        this.blocked = blocked;
        this.customerid = customerid;
    }

    public Account(BigDecimal balance, String currency, Boolean blocked, Long customerid) {
        this.balance = balance;
        this.currency = currency;
        this.blocked = blocked;
        this.customerid = customerid;
    }

    private Long accountnumber;

    private BigDecimal balance;

    private Timestamp creationdate;

    private String currency;

    private Boolean blocked = false;

    private Long customerid;


    public Long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Long accountnumber) {
        this.accountnumber = accountnumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }


    @Override
    public String toString() {
        return "Account N" + accountnumber +
                "\nbalance\t\t" + balance +
                "\ncreationdate\t" + creationdate +
                "\ncurrency\t" + currency + '\'' +
                "\nblocked\t\t" + blocked +
                "\ncustomerid\t" + customerid + "\n";
    }
}
