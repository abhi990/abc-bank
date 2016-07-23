package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-8;
    //using a year as testcase for calculating interestEarned 
    //and also using 10000 milli seconds to be make sure test is being performed exactly after year
    private static final long MILLI_SECS_IN_YEAR= (long)365*24*60*60*1000 +10000;

	
	@Test //testing deposit feature for all accounts
	public void checkDeposit(){
        Account checkingAccount = new CheckingAccount();
        checkingAccount.deposit(100.0);
        assertEquals(100.0, checkingAccount.getBalance(),DOUBLE_DELTA);
        
        Account savingsAccount = new CheckingAccount();
        savingsAccount.deposit(500.0);
        assertEquals(500.0, savingsAccount.getBalance(),DOUBLE_DELTA);
        
        Account maxiAccount = new MaxiAccount();
        maxiAccount.deposit(222.0);
        assertEquals(222, maxiAccount.getBalance(),DOUBLE_DELTA);
	}
	
	@Test //testing withdrawl for different accounts
	public void checkWithdrawl(){
		
        Account checkingAccount = new CheckingAccount();
        checkingAccount.deposit(100.0);
        checkingAccount.withdraw(29);
        assertEquals(71, checkingAccount.getDeposit(), DOUBLE_DELTA);
        
        Account savingsAccount = new CheckingAccount();
        savingsAccount.deposit(500.0);
        savingsAccount.withdraw(69);
        assertEquals(431, savingsAccount.getDeposit(), DOUBLE_DELTA);
        
        Account maxiAccount = new MaxiAccount();
        maxiAccount.deposit(222.0);
        maxiAccount.withdraw(22);
        assertEquals(200, maxiAccount.getDeposit(),DOUBLE_DELTA);
		
	}
	
	@Test // testing rates for checking account
    public void interestRates() {
        
		Account savingsAccount = new SavingsAccount();
        savingsAccount.deposit(1500);
        savingsAccount.setTest(true);
        savingsAccount.setTimePeriod(MILLI_SECS_IN_YEAR);   
        assertEquals(2, savingsAccount.getInterestEarned(), DOUBLE_DELTA);
		savingsAccount.setTest(false);
		
        Account maxiAccount = new MaxiAccount();
        maxiAccount.deposit(3000);
        maxiAccount.setTest(true);
        maxiAccount.setTimePeriod(MILLI_SECS_IN_YEAR);
        assertEquals(150.0, maxiAccount.getInterestEarned(), DOUBLE_DELTA);
        maxiAccount.setTest(false);
        
        
        Account checkingAccount = new CheckingAccount();
        checkingAccount.deposit(100.0);
        checkingAccount.setTest(true);
        checkingAccount.setTimePeriod(MILLI_SECS_IN_YEAR);
        assertEquals(0.1, checkingAccount.getInterestEarned(), DOUBLE_DELTA);
        checkingAccount.setTest(false);
	}

}
