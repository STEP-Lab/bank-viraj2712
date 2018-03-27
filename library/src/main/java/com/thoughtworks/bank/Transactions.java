package com.thoughtworks.bank;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Transactions {

    protected final ArrayList<Transaction> allTransactions;
    private double balance;

    public Transactions() {
        this.allTransactions = new ArrayList<>();
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void credit(double amount, String to) {
        this.allTransactions.add(new CreditTransaction(amount,to));
    }

    public void debit(double amount, String from) {
        this.allTransactions.add(new DebitTransaction(amount,from));
    }

    public void calculateBalance() {
        this.balance = 0;
        for (Transaction transaction : allTransactions) {
            if (transaction instanceof CreditTransaction) {
                this.balance += transaction.getAmount();
            } else if (transaction instanceof DebitTransaction) {
                this.balance -= transaction.getAmount();
            }
        }
    }

    public double getBalance() {
        calculateBalance();
        return this.balance;
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
            if (transaction instanceof CreditTransaction) {
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions getAllDebitTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction instanceof DebitTransaction) {
                transactions.allTransactions.add(transaction);
            }
        }
        return transactions;
    }

    public void print(PrintWriter printWriter) {
        for (Transaction transaction : allTransactions) {
            printWriter.println(transaction.toString());
        }
    }
}