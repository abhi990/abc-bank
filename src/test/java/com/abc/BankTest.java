package com.abc;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
	
	private static final double DOUBLE_DELTA = 1e-8;
    private static final long MILLI_SECS_IN_YEAR= (long)365*24*60*60*1000 + 100000;
    
    private DateProvider dateFactory = DateProvider.getInstance();

    @Test //customer summaries for bank/bankManager
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);
        
        Customer ram = new Customer("Ram");
        ram.openAccount(new CheckingAccount());
        ram.openAccount(new SavingsAccount());
        ram.openAccount(new MaxiAccount());
        bank.addCustomer(ram);
        
        Customer lin = new Customer("Lin");
        lin.openAccount(new CheckingAccount());
        lin.openAccount(new MaxiAccount());
        bank.addCustomer(lin);

        assertEquals("Customer Summary\n - John (1 account)\n - Ram (3 accounts)\n - Lin (2 accounts)", bank.customerSummary());
    }
    
    @Test // total interest to be paid by bank
    public void checkTotalInterest(){
    	
    	//dateFactory.setTestDate(new Date(dateFactory.now().getTime()+MILLI_SECS_IN_YEAR));
    	
    	// customer 1
        Bank bank = new Bank();
        Customer john = new Customer("John");
        Account acc1 = new CheckingAccount();
        john.openAccount(acc1);
        acc1.deposit(1000);
        acc1.setTest(true);
        acc1.setTimePeriod(MILLI_SECS_IN_YEAR);
        
        bank.addCustomer(john);
        
        //customer 2
        Customer ram = new Customer("Ram");
        Account acc2 = new CheckingAccount();
        ram.openAccount(acc2);
        acc2.deposit(1000);
        acc2.setTest(true);
        acc2.setTimePeriod(MILLI_SECS_IN_YEAR);
        
        Account acc3 = new SavingsAccount();
        ram.openAccount(acc3);
        acc3.deposit(2000);
        acc3.setTest(true);
        acc3.setTimePeriod(MILLI_SECS_IN_YEAR);
        
        Account acc4 = new MaxiAccount();
        ram.openAccount(acc4);
        acc4.deposit(1000);
        acc4.setTest(true);
        acc4.setTimePeriod(MILLI_SECS_IN_YEAR);
        
        bank.addCustomer(ram);
        
        assertEquals(55.0, bank.totalInterestPaid(), DOUBLE_DELTA);
        acc1.setTest(false); acc2.setTest(false); acc3.setTest(false); acc4.setTest(false);
    	
    }

}
