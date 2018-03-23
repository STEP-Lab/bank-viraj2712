package com.thoughtworks.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {

    protected CreditTransaction(Date date, double amount, String beneficiary) {
        super(date,amount,beneficiary);
    }

    public CreditTransaction(double amount, String beneficiary) {
        this(new Date(), amount, beneficiary);
    }
}
