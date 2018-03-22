package com.thoughtworks.bank;

import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumberException.class)
    public void validateAccountNumberWithAlphabets() throws InvalidAccountNumberException {
        new AccountNumber("ab12-cd34");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void validateAccountNumberWithSpecialCharacters() throws InvalidAccountNumberException {
        new AccountNumber("12%$=ab&*");
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void validateAccountNumberWithAllNumbers() throws InvalidAccountNumberException {
        new AccountNumber("123456789");
    }
}
