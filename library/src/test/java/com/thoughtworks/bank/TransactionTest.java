package com.thoughtworks.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {

    @Test
    public void checkTransactionDate() {
        Date date = new Date();
        Transaction transaction = new CreditTransaction(date, 1000, "Omkar");
        assertThat(transaction.getDate(),is(date));
    }
}