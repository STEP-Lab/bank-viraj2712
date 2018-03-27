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
        CreditTransaction omkarCredit = new CreditTransaction(new Date(), 1000, "Omkar");
        assertThat(transactions.allTransactions,hasItem(omkarCredit));
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(1000,"Harshad");
        DebitTransaction harshadDebit = new DebitTransaction(new Date(), 1000, "Harshad");
        assertThat(transactions.allTransactions,hasItem(harshadDebit));
    }

    @Test
    public void mustRecordMultipleTransactions() {
        transactions.credit(1000,"Omkar");
        transactions.debit(1000,"Harshad");
        CreditTransaction omkarCredit = new CreditTransaction(new Date(), 1000, "Omkar");
        DebitTransaction harshadDebit = new DebitTransaction(new Date(), 1000, "Harshad");
        assertThat(transactions.allTransactions,hasItems(omkarCredit,harshadDebit));
    }

    @Test
    public void filterTransactionsAboveAmount() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(1000,"Harshad");
        transactions.debit(2000,"Harshad");
        CreditTransaction omkarCredit = new CreditTransaction(1500.0, "Omkar");
        DebitTransaction harshadDebit = new DebitTransaction(2000.0, "Harshad");
        Transactions filteredTransactions = this.transactions.filterByAmountGreaterThan(1500);
        assertThat(filteredTransactions.allTransactions,hasItems(omkarCredit,harshadDebit));
    }

    @Test
    public void filterTransactionsBelowAmount() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(1000,"Harshad");
        transactions.debit(2000,"Harshad");
        DebitTransaction omkarDebit = new DebitTransaction(1000.0, "Omkar");
        CreditTransaction harshadCredit = new CreditTransaction(1000.0, "Harshad");
        Transactions filteredTransactions = this.transactions.filterByAmountBelowThan(1500);
        assertThat(filteredTransactions.allTransactions,hasItems(omkarDebit,harshadCredit));
    }

    @Test
    public void getAllCreditTransactions() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(1000,"Harshad");
        transactions.debit(2000,"Harshad");
        CreditTransaction omkarCredit = new CreditTransaction(1500.0, "Omkar");
        CreditTransaction harshadCredit = new CreditTransaction(1000.0, "Harshad");
        Transactions creditTransactions = this.transactions.getAllCreditTransactions();
        assertThat(creditTransactions.allTransactions,hasItems(omkarCredit,harshadCredit));
    }

    @Test
    public void getAllDebitTransactions() {
        transactions.credit(1500,"Omkar");
        transactions.debit(1000,"Omkar");
        transactions.credit(1000,"Harshad");
        transactions.debit(2000,"Harshad");
        DebitTransaction omkarDebit = new DebitTransaction(1000.0, "Omkar");
        DebitTransaction harshadDebit = new DebitTransaction(2000.0, "Harshad");
        Transactions creditTransactions = this.transactions.getAllDebitTransactions();
        assertThat(creditTransactions.allTransactions,hasItems(omkarDebit,harshadDebit));
    }
}
