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

	/*
	 * 
	BigDecimal daysInYear = new BigDecimal(365);
		BigDecimal daysFrac = new BigDecimal(getDaysFromLastUpdate());
		daysFrac = daysFrac.divide(daysInYear, 5, 6);
		BigDecimal rate = new BigDecimal("0.001");
		BigDecimal amount = new BigDecimal(getDepositLongVal());

		return amount.multiply(rate).multiply(daysFrac).setScale(0).longValue();
	 */
	@Override
	public double getInterestAccrued() {
		/*
		 *  avoid double and to increase accuracy to 1e-6, do the following
		 *  multiplying days by 1000
		 *  multiply interest rate by 1000
		 *  
		 */
		return getDeposit()*(0.001)*((double)getDaysFromLastUpdate())/365;
	}
	
	@Override
    public String getAccountStatement() {
    	String s = "Checking Account\n";
    	s += super.getAccountStatement();
    	return s;
    }

}
