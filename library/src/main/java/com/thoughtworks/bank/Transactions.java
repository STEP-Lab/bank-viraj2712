package com.thoughtworks.bank;

import java.util.ArrayList;

public class Transactions {

    protected final ArrayList<Transaction> allTransactions;

    public Transactions() {
        this.allTransactions = new ArrayList<>();
    }

    public void credit(double amount, String to) {
        this.allTransactions.add(new CreditTransaction(amount,to));
    }

    public void debit(double amount, String from) {
        this.allTransactions.add(new DebitTransaction(amount,from));
    }
}