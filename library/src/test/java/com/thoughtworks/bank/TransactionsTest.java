package com.thoughtworks.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setUp() throws Exception {
        transactions = new Transactions();
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1000,"Omkar");
        assertThat(transactions.allTransactions,hasItem(new CreditTransaction(new Date(), 1000, "Omkar")));
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(1000,"Harshad");
        assertThat(transactions.allTransactions,hasItem(new DebitTransaction(new Date(), 1000, "Harshad")));
    }

    @Test
    public void mustRecordMultipleTransactions() {
        transactions.credit(1000,"Omkar");
        transactions.debit(1000,"Harshad");
        assertThat(transactions.allTransactions,hasItems(new CreditTransaction(new Date(), 1000, "Omkar"),new DebitTransaction(new Date(), 1000, "Harshad")));
    }
}
