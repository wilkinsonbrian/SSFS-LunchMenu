package org.ssfs.ssfslunch;


import java.util.HashMap;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

public class LunchAppWidgetProvider extends AppWidgetProvider {
	
	private String todaysMenu;
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		ComponentName thisWidget = new ComponentName(context, LunchAppWidgetProvider.class);
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.lunch_widget_layout);
		Today date = new Today();
		String todaysDate = date.getTodaysDate();
		
		//Old method of getting the menu
		//LunchMenu menu = new LunchMenu(context, todaysDate);
		//String todaysMenu = menu.getMenu();
		
		//New method of getting the menu
		HashMap<String, String> menu;
		try {
			menu = new WebLunchMenu().execute(new String[] {todaysDate}).get();
			
			if (menu.containsKey(todaysDate)) {
				
				String formattedMenu = "";
				String courses[] = menu.get(todaysDate).split(",");
				
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
		views.setTextViewText(R.id.widget_lunch_menu, todaysMenu);
		
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(thisWidget, views);
	
	}
	

}