/**
 * 
 */
package com.abc;

/**
 * @author abhi9
 *
 */
public class CheckingAccount extends Account {

	public CheckingAccount() {
		super();
	}

	@Override
	public double getInterestAccrued() {
		return getDeposit()*(0.001)*((double)getDaysFromLastUpdate())/365;
	}
	
	@Override
    public String getAccountStatement() {
    	String s = "Checking Account\n";
    	s += super.getAccountStatement();
    	return s;
    }

}
