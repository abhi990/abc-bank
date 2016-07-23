package com.abc;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Transaction {
	
    private final double amount;
    private final Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount ;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public double getAmount() {
		return amount;
	}

}
