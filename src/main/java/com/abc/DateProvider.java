package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }
    
    public Date now(){
    	return Calendar.getInstance().getTime();
    }
    
    /*
     * to give initial withdrawl time for a maxi account as  year 1970 
     */
    public Date start(){
    	return new Date(0);
    }
    
}
