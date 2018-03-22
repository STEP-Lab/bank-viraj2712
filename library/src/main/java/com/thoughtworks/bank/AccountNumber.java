package com.thoughtworks.bank;

public class AccountNumber {

    public AccountNumber(String accountNumber) throws InvalidAccountNumberException {
        validateAccountNumber(accountNumber);
    }

    private static void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if (!accountNumber.matches("\\d{4}-\\d{4}")) {
            throw new InvalidAccountNumberException();
        }
    }
}