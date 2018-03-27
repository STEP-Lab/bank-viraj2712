package com.thoughtworks.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;
    private CreditTransaction omkarCredit;
    private DebitTransaction omkarDebit;
    private CreditTransaction harshadCredit;
    private DebitTransaction harshadDebit;

    @Before
    public void setUp() {
        transactions = new Transactions();
        omkarCredit = new CreditTransaction(1500.0, "Omkar");
        omkarDebit = new DebitTransaction(1000.0, "Omkar");
        harshadCredit = new CreditTransaction(1000.0, "Harshad");
        harshadDebit = new DebitTransaction(2000.0, "Harshad");
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1500,"Omkar");
        assertThat(transactions.allTransactions,hasItem(omkarCredit));
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(2000,"Harshad");
        assertThat(transactions.allTransactions,hasItem(harshadDebit));
    }

    @Test
    public void mustRecordMultipleTransactions() {
        transactions.credit(1500,"Omkar");
        transactions.debit(2000,"Harshad");
        assertThat(transactions.allTransactions,hasItems(omkarCredit, harshadDebit));
    }

    @Test
    public void filterTransactionsAboveAmount() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(1000,"Harshad");
        transactions.debit(2000,"Harshad");
        Transactions filteredTransactions = this.transactions.filterByAmountGreaterThan(1500);
        assertThat(filteredTransactions.allTransactions,hasItems(omkarCredit, harshadDebit));
    }

    @Test
    public void filterTransactionsBelowAmount() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(1000,"Harshad");
        transactions.debit(2000,"Harshad");
        Transactions filteredTransactions = this.transactions.filterByAmountBelowThan(1500);
        assertThat(filteredTransactions.allTransactions,hasItems(omkarDebit, harshadCredit));
    }

    @Test
    public void shouldGiveAllCreditTransactions() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(1000,"Harshad");
        transactions.debit(2000,"Harshad");
        Transactions creditTransactions = this.transactions.getAllCreditTransactions();
        assertThat(creditTransactions.allTransactions,hasItems(omkarCredit,harshadCredit));
    }

    @Test
    public void shouldGiveAllDebitTransactions() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(1000,"Harshad");
        transactions.debit(2000,"Harshad");
        Transactions creditTransactions = this.transactions.getAllDebitTransactions();
        assertThat(creditTransactions.allTransactions,hasItems(omkarDebit,harshadDebit));
    }
}
