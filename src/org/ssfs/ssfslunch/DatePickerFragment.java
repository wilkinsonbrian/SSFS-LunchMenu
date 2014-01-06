package org.ssfs.ssfslunch;


import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	/*
	 * The use of the interface below allows the information from this fragment to be accessed
	 * by the main Activity.  The mCallback could be any variable name.
	 */
	ReturnDateInformation mCallback;
	
	public interface ReturnDateInformation {
		public void dateSelected(String lunchDate);
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (ReturnDateInformation) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ReturnDateInformation");
        }
    }
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		/*
		 * Converts the month and day (which are integers) into a string.
		 * Ex: 11, 16 becomes December 16
		 */
		String todaysDate = dateToString(month) + " " + Integer.toString(day);
		mCallback.dateSelected(todaysDate);
	}
	
	private String dateToString(int month) {
		String months[] = new String[12];
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
		
		return (months[month]);
	}
	
}