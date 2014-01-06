package org.ssfs.ssfslunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class WebLunchMenu extends AsyncTask<String, Void, HashMap<String, String>> {
	
	private HashMap<String, String> menu;
	//private String menuDate;
	
	@Override
    protected HashMap<String, String> doInBackground(String... lunchDate) {
		//menuDate = lunchDate[0];
		DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://grover.ssfs.org/tech/lunchmenu.txt");
		menu = new HashMap<String, String>();
		
		try {
			HttpResponse execute = client.execute(httpGet);
	        InputStream content = execute.getEntity().getContent();

	        BufferedReader infile = new BufferedReader(new InputStreamReader(content));
			String currentLine;
			
			while ((currentLine = infile.readLine()) != null) {
				String parts[] = currentLine.split(":"); //date separated from courses by a colon
				menu.put(parts[0], parts[1]);
			}
			
		} catch (IOException e) {
			Log.e("DEBUGTAB", "What goes here?", e);
		}
		
		return menu;
	}
}
