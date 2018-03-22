package com.thoughtworks.bank;

public class InvalidAccountNumberException extends Exception {
    public InvalidAccountNumberException() {
        super("Invalid account number");
    }
}
