package com.relurori.sandbox.spreadsheetjson;

import com.relurori.sandbox.R;

import android.app.Activity;
import android.os.Bundle;

public class SpreadsheetJsonActivity extends Activity {

	private static final String TAG = SpreadsheetJsonActivity.class.getSimpleName();
	
	private String KEY = "0Al2dZjkUnC2OdEtNaXJhSmE0djBNOVRhSEZ6ZTVnaWc";
	private String URL = "https://spreadsheets.google.com/feeds/list/" + KEY + "/od6/public/values?alt=json";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spreadsheet_demo);
		
		
	}
}
