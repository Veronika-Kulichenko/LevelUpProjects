package com.ivanovcorporation.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by java on 20.02.2016.
 */
public class Transaction {

    public Transaction(Long id, BigDecimal amount, Timestamp date, String operationtype, Long accountnumber) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.operationtype = operationtype;
        this.accountnumber = accountnumber;
    }

    public Transaction(BigDecimal amount, String operationtype, Long accountnumber) {
        this.amount = amount;
        this.operationtype = operationtype;
        this.accountnumber = accountnumber;
    }

    private Long id;

    private BigDecimal amount;

    private Timestamp date;

    private String operationtype;

    private Long accountnumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    public Long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Long accountnumber) {
        this.accountnumber = accountnumber;
    }

    @Override
    public String toString() {
        return "\nTransaction N" + id +
                "\namount\t\t" + amount +
                "\ndate\t\t" + date +
                "\noperationtype\t'" + operationtype + '\'' +
                "\naccountnumber\t" + accountnumber;
    }
}
