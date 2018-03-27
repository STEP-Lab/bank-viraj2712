package com.thoughtworks.bank;

import java.util.ArrayList;

public class Transactions {

    protected final ArrayList<Transaction> allTransactions;
    private Transactions allDebitTransactions;

    public Transactions() {
        this.allTransactions = new ArrayList<>();
    }

    public void credit(double amount, String to) {
        this.allTransactions.add(new CreditTransaction(amount,to));
    }

    public void debit(double amount, String from) {
        this.allTransactions.add(new DebitTransaction(amount,from));
    }

    public Transactions filterByAmountGreaterThan(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction.getAmount() >= amount) {
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions filterByAmountBelowThan(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction.getAmount() <= amount) {
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions getAllCreditTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction.isCreditTransaction()) {
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions getAllDebitTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction.isDebitTransaction()) {
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions;
    }
}