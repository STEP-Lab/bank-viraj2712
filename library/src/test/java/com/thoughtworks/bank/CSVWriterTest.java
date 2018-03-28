package com.thoughtworks.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class CSVWriterTest {
    private ArrayList<String> expected;
    private PrintWriter printWriter;
    private Date date;
    private String[] headers;
    private CSVWriter csvWriter;

    @Before
    public void setUp() throws Exception {
        expected = new ArrayList<>();
        printWriter = new PrintWriter("transactions.txt", "UTF-8"){
            @Override
            public void println(String x) {
                expected.add(x);
            }
        };
        date = new Date();
        headers = new String[] {"Source","Amount","Date"};
        csvWriter = new CSVWriter(printWriter, headers);
    }

    @Test
    public void shouldWritePassedTransactionsToCSVFile() {
        csvWriter.write(new DebitTransaction(date,1000,"Harshad", 1000));
        csvWriter.close();
        assertThat(expected,hasItems(String.join(",", Arrays.asList(headers)),new DebitTransaction(date,1000.0,"Harshad", 1000.0).toCSV()));
    }

    @Test
    public void shouldWriteCSVOfListToFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new DebitTransaction(date, 120,"Viraj", 120));
        transactions.add(new DebitTransaction(date, 1230,"Harshad", 1230));
        transactions.add(new DebitTransaction(date, 1220,"Omkar", 1220));
        csvWriter.write(transactions);
        csvWriter.close();
        assertThat(expected.size(), is(4));
        assertThat(expected, hasItems(String.join(",", Arrays.asList(headers))
                ,new DebitTransaction(date, 120.0,"Viraj", 120.0).toCSV()
                ,new DebitTransaction(date, 1230.0,"Harshad", 1230.0).toCSV()
                ,new DebitTransaction(date, 1220.0,"Omkar", 1220.0).toCSV()));
    }
}
