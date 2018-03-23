package com.thoughtworks.bank;

import java.util.ArrayList;

public class Transactions {

    protected final ArrayList<Transaction> allTransactions;

    public Transactions() {
        this.allTransactions = new ArrayList<>();
    }

    public void credit(double amount, String beneficiary) {
        this.allTransactions.add(new CreditTransaction(amount,beneficiary));
    }

    public void debit(double amount, String beneficiary) {
        this.allTransactions.add(new DebitTransaction(amount,beneficiary));
    }
}
