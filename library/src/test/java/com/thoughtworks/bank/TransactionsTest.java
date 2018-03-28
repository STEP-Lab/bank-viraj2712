package com.thoughtworks.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;
    private CreditTransaction omkarCredit;
    private DebitTransaction omkarDebit;
    private CreditTransaction harshadCredit;
    private DebitTransaction harshadDebit;
    private SimpleDateFormat dateFormatter;
    private Date marchTwentyFive, marchTwentySix, marchTwentySeven, marchTwentyEight;

    @Before
    public void setUp() throws ParseException {
        transactions = new Transactions();
        omkarCredit = new CreditTransaction(1200.0, "Omkar", 0.0);
        omkarDebit = new DebitTransaction(800.0, "Omkar", 1200.0);
        harshadCredit = new CreditTransaction(600.0, "Harshad", 400.0);
        harshadDebit = new DebitTransaction(500.0, "Harshad", 1000.0);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        marchTwentyFive = dateFormatter.parse("25-03-2018");
        marchTwentySix = dateFormatter.parse("26-03-2018");
        marchTwentySeven = dateFormatter.parse("27-03-2018");
        marchTwentyEight = dateFormatter.parse("28-03-2018");
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1200,"Omkar");
        assertThat(transactions.allTransactions,hasItem(omkarCredit));
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(500,"Harshad");
        DebitTransaction harshadDebit1 = new DebitTransaction(500.0, "Harshad", 0.0);
        assertThat(transactions.allTransactions,hasItem(harshadDebit1));
    }

    @Test
    public void mustRecordMultipleTransactions() {
        transactions.credit(1200,"Omkar");
        transactions.debit(500,"Harshad");
        CreditTransaction omkarCredit1 = new CreditTransaction(1200.0, "Omkar", 0.0);
        DebitTransaction harshadDebit1 = new DebitTransaction(500.0, "Harshad", 1200.0);
        assertThat(transactions.allTransactions,hasItems(omkarCredit1, harshadDebit1));
    }

    @Test
    public void shouldCalculateBalance() {
        transactions.credit(2000,"Omkar");
        transactions.credit(1600,"Omkar");
        transactions.debit(500,"Harshad");
        assertThat(transactions.getBalance(),is(3100.0));
    }

    @Test
    public void filterTransactionsAboveAmount() {
        transactions.credit(1200,"Omkar");
        transactions.debit(800,"Omkar");
        transactions.credit(600,"Harshad");
        transactions.debit(500,"Harshad");
        Transactions filteredTransactions = transactions.filterByAmountGreaterThan(1000);
        assertThat(filteredTransactions.allTransactions,hasItems(omkarCredit));
    }

    @Test
    public void filterTransactionsBelowAmount() {
        transactions.credit(1200,"Omkar");
        transactions.debit(800,"Omkar");
        transactions.credit(600,"Harshad");
        transactions.debit(500,"Harshad");
        Transactions filteredTransactions = transactions.filterByAmountBelowThan(1000);
        assertThat(filteredTransactions.allTransactions,hasItems(omkarDebit, harshadCredit, harshadDebit));
    }

    @Test
    public void shouldGiveAllCreditTransactions() {
        transactions.credit(1200,"Omkar");
        transactions.debit(800,"Omkar");
        transactions.credit(600,"Harshad");
        transactions.debit(500,"Harshad");
        Transactions creditTransactions = transactions.getAllCreditTransactions();
        assertThat(creditTransactions.allTransactions,hasItems(omkarCredit,harshadCredit));
    }

    @Test
    public void shouldGiveAllDebitTransactions() {
        transactions.credit(1200,"Omkar");
        transactions.debit(800,"Omkar");
        transactions.credit(600,"Harshad");
        transactions.debit(500,"Harshad");
        Transactions creditTransactions = transactions.getAllDebitTransactions();
        assertThat(creditTransactions.allTransactions,hasItems(omkarDebit,harshadDebit));
    }

    @Test
    public void shouldGiveTransactionsOfGivenDate() throws ParseException {
        transactions.credit(marchTwentyFive,1200,"Omkar");
        transactions.debit(marchTwentySeven,800,"Omkar");
        transactions.credit(marchTwentySeven,600,"Harshad");
        transactions.debit(marchTwentyEight,500,"Harshad");
        DebitTransaction omkarDebit = new DebitTransaction(marchTwentySeven,800.0, "Omkar", 1200.0);
        CreditTransaction harshadCredit = new CreditTransaction(marchTwentySeven,600.0, "Harshad", 400.0);
        Transactions expected = transactions.getTransactionsHappenedOn("27-03-2018");
        assertThat(expected.allTransactions,hasItems(omkarDebit,harshadCredit));
    }

    @Test
    public void shouldGiveTransactionsBeforeGivenDate() throws ParseException {
        transactions.credit(marchTwentyFive,1200,"Omkar");
        transactions.debit(marchTwentySix,800,"Omkar");
        transactions.credit(marchTwentySeven,600,"Harshad");
        transactions.debit(marchTwentyEight,500,"Harshad");
        CreditTransaction omkarCredit = new CreditTransaction(marchTwentyFive,1200.0, "Omkar", 0.0);
        DebitTransaction omkarDebit = new DebitTransaction(marchTwentySix,800.0, "Omkar", 1200.0);
        Transactions expected = transactions.getTransactionsHappenedBefore("27-03-2018");
        assertThat(expected.allTransactions,hasItems(omkarCredit,omkarDebit));
    }

    @Test
    public void shouldGiveTransactionsAfterGivenDate() throws ParseException {
        transactions.credit(marchTwentyFive,1200,"Omkar");
        transactions.debit(marchTwentySeven,800,"Omkar");
        transactions.credit(marchTwentySeven,600,"Harshad");
        transactions.debit(marchTwentyEight,500,"Harshad");
        CreditTransaction harshadCredit = new CreditTransaction(marchTwentySeven,600.0, "Harshad", 400.0);
        DebitTransaction harshadDebit = new DebitTransaction(marchTwentyEight,500.0, "Harshad", 1000.0);
        Transactions expected = transactions.getTransactionsHappenedAfter("26-03-2018");
        assertThat(expected.allTransactions,hasItems(harshadCredit,harshadDebit));
    }

    @Test
    public void shouldGiveTransactionsBetweenGivenPeriod() throws ParseException {
        transactions.credit(marchTwentyFive,1200,"Omkar");
        transactions.debit(marchTwentySix,800,"Omkar");
        transactions.credit(marchTwentySeven,600,"Harshad");
        transactions.debit(marchTwentyEight,500,"Harshad");
        DebitTransaction omkarDebit = new DebitTransaction(marchTwentySix,800.0, "Omkar", 1200.0);
        CreditTransaction harshadCredit = new CreditTransaction(marchTwentySeven,600.0, "Harshad", 400.0);
        DebitTransaction harshadDebit = new DebitTransaction(marchTwentyEight,500.0, "Harshad", 1000.0);
        Transactions expected = transactions.getTransactionsHappenedBetween("26-03-2018","28-03-2018");
        assertThat(expected.allTransactions,hasItems(omkarDebit,harshadCredit,harshadDebit));
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
        transactions.debit(500,"Harshad");
        CreditTransaction omkarCredit = new CreditTransaction(1200.0, "Omkar", 0.0);
        DebitTransaction harshadDebit = new DebitTransaction(500.0, "Harshad", 1200.0);
        transactions.print(printWriter);
        printWriter.close();
        assertThat(expected,hasItems(omkarCredit.toString(),harshadDebit.toString()));
    }
}
