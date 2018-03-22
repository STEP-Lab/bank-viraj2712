package com.thoughtworks.bank;

public class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) throws MinimumBalanceException, InvalidAccountNumberException {
        validateAccountNumber(accountNumber);
        this.accountNumber = accountNumber;
        validateBalance(balance,"Insufficient balance to create an account");
        this.balance = balance;
    }

    private static void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumberException();
        }
    }

    private static void validateBalance(double balance, String message) throws MinimumBalanceException {
        if (balance < 1000.0){
            throw new MinimumBalanceException(message);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) throws MinimumBalanceException {
        if (amount < 0) {
            throw new MinimumBalanceException("Invalid credit request");
        }
        balance += amount;
    }

    public void debit(double amount) throws MinimumBalanceException {
        validateBalance(balance - amount,"Debit declined due to low balance");
        balance -= amount;
    }
}
