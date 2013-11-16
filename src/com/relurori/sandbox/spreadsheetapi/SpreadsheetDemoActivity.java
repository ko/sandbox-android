package com.relurori.sandbox.spreadsheetapi;

import com.relurori.sandbox.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class SpreadsheetDemoActivity extends Activity  {

	private static final String TAG = SpreadsheetDemoActivity.class
			.getSimpleName();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spreadsheet_demo);

	}
}
