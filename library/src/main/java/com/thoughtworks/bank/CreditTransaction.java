package com.thoughtworks.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {
    public CreditTransaction(Date date, double amount, String beneficiary) {
        super(date,amount,beneficiary);
    }
}
