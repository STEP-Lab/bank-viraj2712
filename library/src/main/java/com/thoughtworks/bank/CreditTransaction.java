package com.thoughtworks.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {

    protected CreditTransaction(Date date, double amount, String to) {
        super(date,amount,to);
        super.isCredit = true;
    }

    public CreditTransaction(double amount, String to) {
        this(new Date(), amount, to);
    }
}
