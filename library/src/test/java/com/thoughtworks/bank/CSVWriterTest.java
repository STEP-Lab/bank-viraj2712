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
        headers = new String[] {"Source","Amount","Date","Balance","Type"};
        csvWriter = new CSVWriter(printWriter, headers);
    }

    @Test
    public void shouldWritePassedTransactionsToCSVFile() {
        csvWriter.write(new DebitTransaction(1000,"Harshad", 1000));
        csvWriter.close();
        assertThat(expected,hasItems(String.join(",", Arrays.asList(headers)),new DebitTransaction(1000d,"Harshad", 1000d).toCSV()));
    }

    @Test
    public void shouldWriteCSVOfListToFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new CreditTransaction(1000,"Viraj", 1000));
        transactions.add(new DebitTransaction(500,"Harshad", 500));
        transactions.add(new CreditTransaction(800,"Omkar", 800));
        csvWriter.write(transactions);
        csvWriter.close();
        assertThat(expected.size(), is(4));
        assertThat(expected, hasItems(String.join(",", Arrays.asList(headers))
                ,new CreditTransaction(1000d,"Viraj", 1000d).toCSV()
                ,new DebitTransaction(500d,"Harshad", 500d).toCSV()
                ,new CreditTransaction(800d,"Omkar", 800d).toCSV()));
    }
}
