package com.thoughtworks.bank;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    protected final Date date;
    protected final double amount;
    protected final String source;
    protected double currentBalance;

    public Transaction(Date date, double amount, String source, double currentBalance) {
        this.date = date;
        this.amount = amount;
        this.source = source;
        this.currentBalance = currentBalance;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Double.compare(that.currentBalance, currentBalance) == 0 &&
                Objects.equals(date.toString(), that.date.toString()) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date.toString(), amount, source, currentBalance);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                ", currentBalance=" + currentBalance +
                '}';
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s",source,amount,date,currentBalance,this.getClass().getSimpleName());
    }
}