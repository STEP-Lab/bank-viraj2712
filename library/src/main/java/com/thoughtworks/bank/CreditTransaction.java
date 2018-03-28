package com.thoughtworks.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {

    protected CreditTransaction(Date date, double amount, String to, double currentBalance) {
        super(date,amount,to,currentBalance);
    }

    public CreditTransaction(double amount, String to, double currentBalance) {
        this(new Date(), amount, to,currentBalance);
    }
}
