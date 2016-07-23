package com.abc;

import java.util.Date;

public class MaxiAccount extends Account {

	private Date lastWithdrawlDate ;
	
	public Date getLastWithDrawlDate(){
		return lastWithdrawlDate;
	}
	public MaxiAccount() {
		super();
		lastWithdrawlDate = DateProvider.getInstance().start();
	}
	
	@Override
	public double getInterestAccrued() {
		if (checkLastWithdrawlDate()){
			return getDeposit() * (0.001)*((double)(getDaysFromLastUpdate())/(double)365);
		}else{
			return getDeposit()*(0.05)*((double)(getDaysFromLastUpdate())/(double)365);
		}
	}

	@Override
	public void withdraw(double amount) {
		super.withdraw(amount);
		lastWithdrawlDate = DateProvider.getInstance().now();
    }
	
	@Override
    public String getAccountStatement() {
    	String s = "Maxi Savings Account\n";
    	s += super.getAccountStatement();
    	return s;
    }
	
	/*
	 * checks if the last withdrawl occured less than 10 days back
	 */
    private boolean checkLastWithdrawlDate() {
    	Date tDate = getLastWithDrawlDate();
    	Date cDate = DateProvider.getInstance().now();
    	long timeDiff = cDate.getTime()- tDate.getTime();
    	long daysDiff = timeDiff/((long)1000 * 60 * 60 * 24);
    	if(daysDiff <= 10 ) 
    		return true;
    	else 
    		return false;
	}

}
