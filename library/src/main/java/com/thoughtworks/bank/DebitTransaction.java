package com.thoughtworks.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {
    protected DebitTransaction(Date date, double amount, String beneficiary) {
        super(date,amount,beneficiary);
    }
}
