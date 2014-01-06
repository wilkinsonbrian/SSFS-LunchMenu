package org.ssfs.ssfslunch;


import java.util.HashMap;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements DatePickerFragment.ReturnDateInformation {

	private String todaysMenu;
	private String classDate; //used to keep track of date if app ends
	private static final String STATE_DATE = "lunchDate";
	private static final String STATE_MENU = "lunchMenu";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		// Check whether we're recreating a previously destroyed instance
	    if (savedInstanceState != null) {
	        // Restore value of members from saved state
	        classDate = savedInstanceState.getString(STATE_DATE);
	        todaysMenu = savedInstanceState.getString(STATE_MENU);
	        TextView lunchMenu = (TextView) findViewById(R.id.lunch_menu);
			TextView dateOfLunch = (TextView) findViewById(R.id.titleDate);
	        dateOfLunch.setText(classDate);
			lunchMenu.setText(todaysMenu);
	    } 
	}
	/*
	 * Creates the fragment that holds the Date Picker.  Allows the user to select
	 * the desired date and returns that date to the Main Activity.
	 */
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ssfs.ssfslunch.DatePickerFragment.ReturnDateInformation#dateSelected(java.lang.String)
	 * Recieves the date picked by the user and selects the lunch associated with that date.
	 * The menu information is placed in the lunchMenu TextView (see activity_main.xml).
	 */
	@Override
	public void dateSelected(String lunchDate) {
		classDate = lunchDate;
		HashMap<String, String> menu;
		
		//LunchMenu menu = new LunchMenu(this.getApplicationContext(), lunchDate);
		try {
			menu = new WebLunchMenu().execute(new String[] {lunchDate}).get();
			
			if (menu.containsKey(lunchDate)) {
				
				String formattedMenu = "";
				String courses[] = menu.get(lunchDate).split(",");
				
				for (int i=0; i<courses.length; i++) {
					formattedMenu += courses[i] + "\n";
				}
				
				todaysMenu = formattedMenu;
			} else {
				todaysMenu = "No Lunch Menu Available";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TextView lunchMenu = (TextView) findViewById(R.id.lunch_menu);
		TextView dateOfLunch = (TextView) findViewById(R.id.titleDate);
		dateOfLunch.setText(lunchDate);
		lunchMenu.setText(todaysMenu);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
	 * In case the screen is rotated or stopped in any other way the values of the date
	 * and the menu for that date are preserved.
	 */
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putString(STATE_DATE, classDate);
		savedInstanceState.putString(STATE_MENU, todaysMenu);
		super.onSaveInstanceState(savedInstanceState);
	}
		
}
	
