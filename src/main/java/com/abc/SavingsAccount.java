package com.abc;

public class SavingsAccount extends Account {

	public SavingsAccount() {
		super();
	}

	@Override
	public double getInterestAccrued() {
        if (getDeposit() <= 1000)
            return getDeposit()*0.001*((double)getDaysFromLastUpdate()/365);
        else
            return (500+(getDeposit()-1000)) * (0.002)*((double)getDaysFromLastUpdate()/365);
	}
	
	@Override
    public String getAccountStatement() {
    	String s = "Savings Account\n";
    	s += super.getAccountStatement();
    	return s;
    }

}
