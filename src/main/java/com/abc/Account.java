package com.abc;

import static java.lang.Math.abs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Account {

    private List<Transaction> transactions;
    private double deposits;  // total funds in account
    //interest earned on the amount in the account till the last transaction
    private double interestEarned;
    //private String accountStatement;
    
    // Date of recent balance Update
    private Date lastUpdatedDate;
    
    public double getBalance() {
		return deposits + getInterestEarned();
	}
    
    public double getDeposit(){
 /*   	BigDecimal temp = new BigDecimal(deposits);
    	BigDecimal v100 = new BigDecimal(100);    	
    	return temp.divide(v100, 2, 6).toPlainString();*/
    	return deposits;
    }

	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	// Interest Earned till Last Transaction
	public double getInterestEarned() {
		return interestEarned+ getInterestAccrued();
	}

	public Account() {
        this.transactions = new ArrayList<>();
        lastUpdatedDate = DateProvider.getInstance().now();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
            double accruedAmt = getInterestAccrued();
            updateInterestEarned(accruedAmt);
            updateDeposit(amount);
            //update lastUpdate Date 
            lastUpdatedDate = DateProvider.getInstance().now();
        }
    }

	public void withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else if( amount >= deposits){
	    	throw new IllegalArgumentException("amount withdrawn must be less than available funds");
	    } else {
	        transactions.add(new Transaction(-amount));
            double accruedAmt = getInterestAccrued();
            updateInterestEarned(accruedAmt);
            //updateBalance(amount, accruedAmt);
            updateDeposit(-amount);
	        lastUpdatedDate = DateProvider.getInstance().now();
	    }
    }
	
    public String getAccountStatement() {
    	String s ="";
        for (Transaction t : getTransactions()) {
            s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount())+ "\n";
        }
        //s += "Deposits " + toDollars(getDeposit());
        //s += "Interest Earned" + toDollars(getInterestEarned()+ getInterestAccrued());
        s += "Total " + toDollars(getDeposit());
        return s;
    }
	
	/*
	 *  Each Account has different types of interest rate 
	 *  and to calculate interest earned from last transaction to uptoDate 
	 *  all subclasses must implement this method to satisfy the definitions
	 */
	protected abstract double getInterestAccrued();
	
	/*	
	 * update balance for accounts with continous compunding interest rate
	 * 
	    protected void updateBalance(double amount, double accruedAmt) {
	    	balance += accruedAmt + amount;
		}*/
	    
	    protected long getDaysFromLastUpdate(){
	    	Date tDate = lastUpdatedDate;
	    	Date cDate = getCurrentDate();
	    	long timeDiff = cDate.getTime() - tDate.getTime();
	    	return timeDiff/((long)1000 * 60 * 60 * 24);
	    }
	
	private void updateDeposit(double amount){
		deposits += amount;
	}

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

	private void updateInterestEarned(double d) {
    	interestEarned += d;
	}
    
	
	/*
	 * memeber used to facilitate testing
	 */
    // variable to check if Testing envi is active
    private boolean test = false;
    private long testPeriod ;
    
    public void setTest(boolean flag){
    	test = flag;
    }
    
    public void setTimePeriod(long milliSecs){
    	testPeriod = milliSecs;
    }

    private Date getCurrentDate(){
    	if(test)
    		return new Date((new Date().getTime())+ testPeriod);
    	else 
    		return DateProvider.getInstance().now();
    }

}
