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
    public void setUp() {
        transactions = new Transactions();
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1000,"Omkar");
        CreditTransaction omkar = new CreditTransaction(new Date(), 1000, "Omkar");
        assertThat(transactions.allTransactions,hasItem(omkar));
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(1000,"Harshad");
        DebitTransaction harshad = new DebitTransaction(new Date(), 1000, "Harshad");
        assertThat(transactions.allTransactions,hasItem(harshad));
    }

    @Test
    public void mustRecordMultipleTransactions() {
        transactions.credit(1000,"Omkar");
        transactions.debit(1000,"Harshad");
        CreditTransaction omkar = new CreditTransaction(new Date(), 1000, "Omkar");
        DebitTransaction harshad = new DebitTransaction(new Date(), 1000, "Harshad");
        assertThat(transactions.allTransactions,hasItems(omkar,harshad));
    }

    @Test
    public void filterTransactionsByAmount() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(2000,"Harshad");
        transactions.debit(1000,"Harshad");
        CreditTransaction omkar = new CreditTransaction(1500.0, "Omkar");
        CreditTransaction harshad = new CreditTransaction(2000.0, "Harshad");
        Transactions filteredTransactions = this.transactions.filterByAmountGreaterThan(1500);
        assertThat(filteredTransactions.allTransactions,hasItems(omkar,harshad));
    }
}
