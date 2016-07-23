package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.getInterestEarned();
        return total;
    }

    
    //
    public String getStatements() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + a.getAccountStatement() + "\n";
            total += a.getBalance();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    /*
     * provide 0-index of from account, to account and amount
     */
    public void transfer(int from, int to, double amount){
    	if(amount <= 0 ){
    		throw new IllegalArgumentException("please provide a valid amount");
    	} else if( to == from ){
    		throw new IllegalArgumentException("please select two different accounts from tranfer");
    	} else if( to > accounts.size() || from > accounts.size()){
    		throw new IllegalArgumentException("please select valid accounts from the list");
    	}
    	
    	synchronized (accounts) {
    		accounts.get(from).withdraw(amount);
    		accounts.get(to).deposit(amount);
		}
    	
    }
}
