package com.thoughtworks.bank;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVWriter {
    private final PrintWriter printWriter;

    public CSVWriter(PrintWriter printWriter, String[] headers) {
        this.printWriter = printWriter;
        printWriter.println(String.join(",", Arrays.asList(headers)));
    }

    public void write(Transaction transaction) {
        printWriter.println(transaction.toCSV());
    }

    public void write(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            printWriter.println(transaction.toCSV());
        }
    }

    public void close() {
        printWriter.close();
    }
}
