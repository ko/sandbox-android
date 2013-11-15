package com.relurori.sandbox.spreadsheetapi;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.relurori.sandbox.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.*;
import java.util.*;


public class SpreadsheetDemoActivity extends Activity {

	private static final String TAG = SpreadsheetDemoActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spreadsheet_demo);
		
		SpreadsheetService service;
		SpreadsheetFeed feed;
		int i = 0;
		try {
		    SpreadsheetEntry spreadsheet;
		    service = new SpreadsheetService("Spreadsheet");
		    service.setProtocolVersion(SpreadsheetService.Versions.V3);
		    service.setUserCredentials("username", "password");//permission required to add in Manifest
		    URL metafeedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
		    feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);

		    List<SpreadsheetEntry> spreadsheets = feed.getEntries();
		    if (spreadsheets.size() > 0) {
		        spreadsheet = spreadsheets.get(i);//Get your Spreadsheet
		        Log.d(TAG,"" + spreadsheet.getTitle());
		   }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
