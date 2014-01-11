package com.relurori.sandbox.localdb;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class DatabaseActivity extends Activity {

	private static final String TAG = DatabaseActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		TestDb db = new TestDb(getBaseContext());
		db.createRecords(1, "name1");
		Cursor cursor = db.selectRecords();
		
		Log.d(TAG,"cursor|getString=" + cursor.getString(1));
	}
}
