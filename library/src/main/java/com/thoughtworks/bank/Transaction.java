package com.thoughtworks.bank;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    protected Date date;
    protected final double balance;
    protected final String beneficiary;

    public Transaction(Date date, double balance, String beneficiary) {
        this.date = date;
        this.balance = balance;
        this.beneficiary = beneficiary;
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
                Objects.equals(beneficiary, that.beneficiary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, beneficiary);
    }
}