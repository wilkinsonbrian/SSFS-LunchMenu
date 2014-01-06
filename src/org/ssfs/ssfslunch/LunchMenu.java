package org.ssfs.ssfslunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import android.content.Context;

public class LunchMenu {
	
	private String lunchDate;
	private Context c;
	
	public LunchMenu(Context context, String date) {
		lunchDate = date;
		c = context;
	}
	
	/*
	 * Reads a text file containing dates and courses separated by commas.  The date is separated
	 * from the courses by a colon.  This method first splits each line into the date (which becomes
	 * the key) and the comma separated courses which become the value of a hash map.
	 */
//	
	public String getMenu() {
		
		InputStream inputStream = c.getResources().openRawResource(R.raw.lunchmenu);
        
        BufferedReader infile = null;

		HashMap<String, String> menu = new HashMap<String, String>();
		
		try {
			
			String currentLine;
			
			infile = new BufferedReader(new InputStreamReader(inputStream));
			while ((currentLine = infile.readLine()) != null) {
				String parts[] = currentLine.split(":"); //date separated from courses by a colon
				menu.put(parts[0], parts[1]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		// If the date is in the file, return the lunch information.  Else return a "date not found
		// string.
		if (menu.containsKey(lunchDate)) {
			
			String formattedMenu = "";
			String courses[] = menu.get(lunchDate).split(",");
			
			for (int i=0; i<courses.length; i++) {
				formattedMenu += courses[i] + "\n";
			}
			
			return (formattedMenu);
		} else {
			return ("No Lunch Menu Available");
		}
	}
}
