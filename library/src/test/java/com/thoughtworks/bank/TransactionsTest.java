package com.thoughtworks.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
        omkarCredit = new CreditTransaction(1200.0, "Omkar");
        omkarDebit = new DebitTransaction(800.0, "Omkar");
        harshadCredit = new CreditTransaction(600.0, "Harshad");
        harshadDebit = new DebitTransaction(1400.0, "Harshad");
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1200,"Omkar");
        assertThat(transactions.allTransactions,hasItem(omkarCredit));
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(1400,"Harshad");
        assertThat(transactions.allTransactions,hasItem(harshadDebit));
    }

    @Test
    public void mustRecordMultipleTransactions() {
        transactions.credit(1200,"Omkar");
        transactions.debit(1400,"Harshad");
        assertThat(transactions.allTransactions,hasItems(omkarCredit, harshadDebit));
    }

    @Test
    public void filterTransactionsAboveAmount() {
        transactions.credit(1200,"Omkar");
        transactions.debit(800,"Omkar");
        transactions.credit(600,"Harshad");
        transactions.debit(1400,"Harshad");
        Transactions filteredTransactions = transactions.filterByAmountGreaterThan(1000);
        assertThat(filteredTransactions.allTransactions,hasItems(omkarCredit, harshadDebit));
    }

    @Test
    public void filterTransactionsBelowAmount() {
        transactions.credit(1200,"Omkar");
        transactions.debit(800,"Omkar");
        transactions.credit(600,"Harshad");
        transactions.debit(1400,"Harshad");
        Transactions filteredTransactions = transactions.filterByAmountBelowThan(1000);
        assertThat(filteredTransactions.allTransactions,hasItems(omkarDebit, harshadCredit));
    }

    @Test
    public void shouldGiveAllCreditTransactions() {
        transactions.credit(1200,"Omkar");
        transactions.debit(800,"Omkar");
        transactions.credit(600,"Harshad");
        transactions.debit(1400,"Harshad");
        Transactions creditTransactions = transactions.getAllCreditTransactions();
        assertThat(creditTransactions.allTransactions,hasItems(omkarCredit,harshadCredit));
    }

    @Test
    public void shouldGiveAllDebitTransactions() {
        transactions.credit(1200,"Omkar");
        transactions.debit(800,"Omkar");
        transactions.credit(600,"Harshad");
        transactions.debit(1400,"Harshad");
        Transactions creditTransactions = transactions.getAllDebitTransactions();
        assertThat(creditTransactions.allTransactions,hasItems(omkarDebit,harshadDebit));
    }

    @Test
    public void shouldWriteTransactionsToGivenStream() throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> expected = new ArrayList<>();
        PrintWriter printWriter = new PrintWriter("foo", "utf-8") {
            @Override
            public void write(String x) {
                expected.add(x);
            }
        };
        transactions.credit(1200,"Omkar");
        transactions.debit(1400,"Harshad");
        CreditTransaction omkarCredit = new CreditTransaction(1200.0, "Omkar");
        DebitTransaction harshadDebit = new DebitTransaction(1400.0, "Harshad");
        transactions.print(printWriter);
        printWriter.close();
        assertThat(expected,hasItems(omkarCredit.toString(),harshadDebit.toString()));
    }
}
