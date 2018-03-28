package com.thoughtworks.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {

    protected DebitTransaction(Date date, double amount, String from, double currentBalance) {
        super(date,amount,from,currentBalance);
    }

    public DebitTransaction(double amount, String from, double currentBalance) {
        this(new Date(), amount, from, currentBalance);
    }
}