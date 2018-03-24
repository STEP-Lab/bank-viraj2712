package com.thoughtworks.bank;

import java.util.ArrayList;

public class Account {
    private String accountHolder;
    private final AccountNumber accountNumber;
    private double balance;
    private Transactions transactions = new Transactions();
    private ArrayList<Transaction> allTransactions = transactions.allTransactions;

    public Account(String accountHolder, AccountNumber accountNumber, double balance) throws MinimumBalanceException, InvalidAccountNumberException {
        this.accountHolder = accountHolder;
        validateBalance(balance,"Insufficient balance to create an account!");
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    private static void validateBalance(double balance, String message) throws MinimumBalanceException {
        if (balance < 1000.0){
            throw new MinimumBalanceException(message);
        }
    }

    private boolean validateCredit(double amount) {
        return amount > 0;
    }

    private boolean validateDebit(double balance, double amount) {
        return balance - amount > 1000;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void credit(double amount) throws MinimumBalanceException {
        if (!validateCredit(amount)) {
            throw new MinimumBalanceException("Invalid credit request!");
        }
        balance += amount;
        transactions.credit(amount,accountHolder);
    }

    public void debit(double amount) throws MinimumBalanceException {
        if (!validateDebit(balance,amount)) {
            throw new MinimumBalanceException("Debit declined due to low balance!");
        }
        balance -= amount;
        transactions.debit(amount,accountHolder);
    }
}