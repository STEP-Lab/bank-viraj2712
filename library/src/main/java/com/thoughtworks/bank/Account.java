package com.thoughtworks.bank;

public class Account {
    private String accountHolder;
    private final AccountNumber accountNumber;
    private double balance;

    public Account(String accountHolder, AccountNumber accountNumber, double balance) throws MinimumBalanceException, InvalidAccountNumberException {
        this.accountHolder = accountHolder;
        validateBalance(balance,"Insufficient balance to create an account");
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    private static void validateBalance(double balance, String message) throws MinimumBalanceException {
        if (balance < 1000.0){
            throw new MinimumBalanceException(message);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) throws MinimumBalanceException {
        if (amount <= 0) {
            throw new MinimumBalanceException("Invalid credit request");
        }
        balance += amount;
    }

    public void debit(double amount) throws MinimumBalanceException {
        validateBalance(balance - amount,"Debit declined due to low balance");
        balance -= amount;
    }
}
