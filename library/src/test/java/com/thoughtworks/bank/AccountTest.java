package com.thoughtworks.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException, InvalidAccountNumberException {
        account = Account.createAccount("Viraj", "1111-2222",2000);
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(),is(2000d));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumberException {
        Account.createAccount("Viraj", "2222-1111", 500);
    }

    @Test
    public void mustRecordAllTransactions() throws MinimumBalanceException {
        account.credit(1000);
        account.debit(500);
        assertThat(account.getAllTransactions(),hasItems(new CreditTransaction(1000,"Viraj",2000d),new DebitTransaction(500,"Viraj",3000d)));
    }

    @Test
    public void validateCreditTransaction() throws MinimumBalanceException {
        account.credit(1000);
        assertThat(account.getBalance(),is(3000d));
    }

    @Test
    public void mustRecordCreditTransaction() throws MinimumBalanceException {
        account.credit(1000);
        assertThat(account.getAllTransactions(),hasItem(new CreditTransaction(1000,"Viraj",2000d)));
    }

    @Test(expected = MinimumBalanceException.class)
    public void validateDeclinedCredit() throws MinimumBalanceException {
        account.credit(-500);
    }

    @Test
    public void validateDebitTransaction() throws MinimumBalanceException {
        account.debit(500);
        assertThat(account.getBalance(),is(1500d));
    }

    @Test
    public void mustRecordDebitTransaction() throws MinimumBalanceException {
        account.debit(500);
        assertThat(account.getAllTransactions(),hasItem(new DebitTransaction(500,"Viraj",2000d)));
    }

    @Test(expected = MinimumBalanceException.class)
    public void validateDeclinedDebit() throws MinimumBalanceException {
        account.debit(1500);
    }
}
