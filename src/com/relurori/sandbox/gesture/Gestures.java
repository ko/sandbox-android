package com.relurori.sandbox.gesture;

import com.relurori.sandbox.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Gestures extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gestures);
		
		Intent i = getIntent();
		String key = i.getStringExtra("genericKey");
	}
}
