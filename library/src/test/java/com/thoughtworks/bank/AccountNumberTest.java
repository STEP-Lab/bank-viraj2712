package com.thoughtworks.bank;

import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumberException.class)
    public void validateAccountNumberWithAlphabets() throws InvalidAccountNumberException {
        AccountNumber.createAccountNumber("ab12-cd34");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void validateAccountNumberWithSpecialCharacters() throws InvalidAccountNumberException {
        AccountNumber.createAccountNumber("12%$=ab&*");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void validateAccountNumberWithAllNumbers() throws InvalidAccountNumberException {
        AccountNumber.createAccountNumber("123456789");
    }
}
