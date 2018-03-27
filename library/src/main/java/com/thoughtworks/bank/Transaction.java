package com.thoughtworks.bank;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    protected final Date date;
    protected final double balance;
    protected final String source;

    public Transaction(Date date, double balance, String source) {
        this.date = date;
        this.balance = balance;
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(date.toString(), that.date.toString()) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date.toString(), balance, source);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", balance=" + balance +
                ", source='" + source + '\'' +
                '}';
    }
}