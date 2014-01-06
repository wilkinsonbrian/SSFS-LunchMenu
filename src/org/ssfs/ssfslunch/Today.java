package org.ssfs.ssfslunch;

import java.util.Calendar;


public class Today {
	
	private String[] months = new String[12];
	private int month;
	private int day;
	
	public Today() {
		final Calendar c = Calendar.getInstance();
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		dateToString();
	}
	
	private void dateToString() {
		months[0] = "January";
		months[1] = "February";
		months[2] = "March";
		months[3] = "April";
		months[4] = "May";
		months[5] = "June";
		months[6] = "July";
		months[7] = "August";
		months[8] = "September";
		months[9] = "October";
		months[10]= "November";
		months[11]= "December";
	}
	
	public String getTodaysDate() {
		String todaysDate = months[month] + " " + Integer.toString(day);
		return (todaysDate);
	}

}
