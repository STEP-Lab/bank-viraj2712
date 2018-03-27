package com.thoughtworks.bank;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

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

    protected void credit(Date date, double amount, String to) {
        this.allTransactions.add(new CreditTransaction(date,amount,to));
    }

    public void debit(double amount, String from) {
        this.allTransactions.add(new DebitTransaction(amount,from));
    }

    protected void debit(Date date, double amount, String from) {
        this.allTransactions.add(new DebitTransaction(date,amount,from));
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
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction.getAmount() >= amount) {
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions filterByAmountBelowThan(double amount) {
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction.getAmount() <= amount) {
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions getAllCreditTransactions() {
        Transactions credits = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction instanceof CreditTransaction) {
                credits.allTransactions.add(transaction);
            }
        }
        return credits;
    }

    public Transactions getAllDebitTransactions() {
        Transactions debits = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction instanceof DebitTransaction) {
                debits.allTransactions.add(transaction);
            }
        }
        return debits;
    }

    public Transactions getTransactionsHappenedOn(Date when) {
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if(transaction.getDate().equals(when)){
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public void print(PrintWriter printWriter) {
        for (Transaction transaction : allTransactions) {
            printWriter.println(transaction.toString());
        }
    }

    public Transactions getTransactionsHappenedBefore(Date when) {
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if(transaction.getDate().before(when)){
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions getTransactionsHappenedAfter(Date when) {
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if(transaction.getDate().after(when)){
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions getTransactionsHappenedDuring(Date from, Date to) {
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if(transaction.getDate().after(from) && transaction.getDate().before(to)){
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
}