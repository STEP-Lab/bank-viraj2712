package com.thoughtworks.bank;

import java.util.ArrayList;

public class Account {
    private String accountHolder;
    private final AccountNumber accountNumber;
    private Transactions transactions;

    public Account(String accountHolder, AccountNumber accountNumber, double balance) throws MinimumBalanceException {
        this.transactions = new Transactions();
        credit(balance);
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
    }

    private static void validateBalance(double balance, String message) throws MinimumBalanceException {
        if (balance < 1000.0){
            throw new MinimumBalanceException(message);
        }
    }

    public static Account createAccount(String accountHolder, String accNumber, double balance) throws MinimumBalanceException, InvalidAccountNumberException {
        validateBalance(balance,"Insufficient balance to create an account!");
        AccountNumber accountNumber = AccountNumber.createAccountNumber(accNumber);
        return new Account(accountHolder, accountNumber, balance);
    }

    private void validateCreditTransaction(double amount) throws MinimumBalanceException {
        if (amount < 0) {
            throw new MinimumBalanceException("Invalid credit request!");
        }
    }

    private void validateDebitTransaction(double amount) throws MinimumBalanceException {
        if (getBalance() - amount < 1000) {
            throw new MinimumBalanceException("Debit declined due to low balance!");
        }
    }

    public double getBalance() {
        return transactions.getBalance();
    }

    public ArrayList<Transaction> getAllTransactions() {
        return transactions.getAllTransactions();
    }

    public void credit(double amount) throws MinimumBalanceException {
        validateCreditTransaction(amount);
        transactions.credit(amount,accountHolder,getBalance());
    }

    public void debit(double amount) throws MinimumBalanceException {
        validateDebitTransaction(amount);
        transactions.debit(amount,accountHolder,getBalance());
    }
}