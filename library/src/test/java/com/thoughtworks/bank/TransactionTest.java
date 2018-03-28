package com.thoughtworks.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {

    private Transaction transaction;
    private Date date;

    @Before
    public void setUp() {
        transaction = new CreditTransaction(new Date(), 1000, "Omkar", 1000);
        date = new Date();
    }

    @Test
    public void checkTransactionDate() {
        assertThat(transaction.getDate(),is(date));
    }

    @Test
    public void mustReturnCSV() {
        assertThat(transaction.toCSV(),is("Omkar,1000.0,"+date.toString()+",1000.0,CreditTransaction"));
    }
}