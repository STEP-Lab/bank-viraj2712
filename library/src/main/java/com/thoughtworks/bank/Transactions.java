package com.thoughtworks.bank;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Transactions {

    protected final ArrayList<Transaction> allTransactions;
    private double balance;
    private SimpleDateFormat dateFormatter;

    public Transactions() {
        this.allTransactions = new ArrayList<>();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void credit(double amount, String to) {
        this.allTransactions.add(new CreditTransaction(amount,to,getBalance()));
    }

    protected void credit(Date date, double amount, String to) {
        this.allTransactions.add(new CreditTransaction(date,amount,to, getBalance()));
    }

    public void debit(double amount, String from) {
        this.allTransactions.add(new DebitTransaction(amount,from,getBalance()));
    }

    protected void debit(Date date, double amount, String from) {
        this.allTransactions.add(new DebitTransaction(date,amount,from, getBalance()));
    }

    public void calculateBalance() {
        this.balance = 0;
        for (Transaction transaction : allTransactions) {
            if (transaction instanceof CreditTransaction) {
                this.balance += transaction.getAmount();
            } else if (transaction instanceof DebitTransaction) {
                this.balance -= transaction.getAmount();
            }
        }
    }

    public double getBalance() {
        calculateBalance();
        return this.balance;
    }

    public Transactions filterByAmountGreaterThan(double amount) {
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction.getAmount() >= amount) {
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions filterByAmountBelowThan(double amount) {
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction.getAmount() < amount) {
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions getAllCreditTransactions() {
        Transactions credits = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction instanceof CreditTransaction) {
                credits.allTransactions.add(transaction);
            }
        }
        return credits;
    }

    public Transactions getAllDebitTransactions() {
        Transactions debits = new Transactions();
        for (Transaction transaction : allTransactions) {
            if (transaction instanceof DebitTransaction) {
                debits.allTransactions.add(transaction);
            }
        }
        return debits;
    }

    public Transactions getTransactionsHappenedOn(String when) throws ParseException {
        Date transactionDate = dateFormatter.parse(when);
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if(transaction.getDate().equals(transactionDate)){
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions getTransactionsHappenedBefore(String when) throws ParseException {
        Date transactionDate = dateFormatter.parse(when);
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if(transaction.getDate().before(transactionDate)){
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions getTransactionsHappenedAfter(String when) throws ParseException {
        Date transactionDate = dateFormatter.parse(when);
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if(transaction.getDate().after(transactionDate)){
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public Transactions getTransactionsHappenedBetween(String from, String to) throws ParseException {
        Date initialDate = dateFormatter.parse(from);
        Date finalDate = dateFormatter.parse(to);
        Transactions filteredTransactions = new Transactions();
        for (Transaction transaction : allTransactions) {
            if(transaction.getDate().compareTo(initialDate) >= 0 && transaction.getDate().compareTo(finalDate) <= 0){
                filteredTransactions.allTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public void print(PrintWriter printWriter) {
        for (Transaction transaction : allTransactions) {
            printWriter.println(transaction.toString());
        }
    }

    public void writeCSVTo(PrintWriter printWriter) {
        String[] headers = {"Date","Amount","To","Balance"} ;
        CSVWriter writer = new CSVWriter(printWriter,headers);
        writer.write(allTransactions);
        writer.close();
    }
}