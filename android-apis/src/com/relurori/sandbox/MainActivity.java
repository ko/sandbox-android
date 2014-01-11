package com.relurori.sandbox;

import com.relurori.sandbox.bluetooth.BluetoothChatActivity;
import com.relurori.sandbox.button.MovingActivity;
import com.relurori.sandbox.camera.crop.external.CameraCropSeperateActivity;
import com.relurori.sandbox.camera.crop.internal.CameraCropInternalActivity;
import com.relurori.sandbox.gesture.GesturesActivity;
import com.relurori.sandbox.localdb.DatabaseActivity;
import com.relurori.sandbox.oauth2.AccountManagerActivity;
import com.relurori.sandbox.preferences.DynamicPreferenceActivity;
import com.relurori.sandbox.slidingmenu.SlidingMenuActivity;
import com.relurori.sandbox.spreadsheet.gdata.SpreadsheetGdataActivity;
import com.relurori.sandbox.spreadsheet.gdata.SpreadsheetGdataMainActivity;
import com.relurori.sandbox.spreadsheet.json.SpreadsheetJsonActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		onCreateSetupButtons();
	}

	private void onCreateSetupButtons() {
		Button b = (Button) findViewById(R.id.movingButtons);
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, MovingActivity.class);
				i.putExtra("genericKey", "value");
				MainActivity.this.startActivity(i);
			}
			
		});
		
		b = (Button) findViewById(R.id.gestureButtons);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, GesturesActivity.class);
				i.putExtra("genericKey", "value");
				MainActivity.this.startActivity(i);
			}
		});		
		
		b = (Button) findViewById(R.id.slidingMenu);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SlidingMenuActivity.class);
				i.putExtra("genericKey", "value");
				MainActivity.this.startActivity(i);
			}
		});
		
		b = (Button) findViewById(R.id.spreadsheetDemo);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SpreadsheetGdataActivity.class);
				MainActivity.this.startActivity(i);
			}
		});
		
		b = (Button) findViewById(R.id.oauth2);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, AccountManagerActivity.class);
				MainActivity.this.startActivity(i);
			}
		});
		
		b = (Button) findViewById(R.id.preferences);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, DynamicPreferenceActivity.class);
				MainActivity.this.startActivity(i);
			}
		});
		

		b = (Button) findViewById(R.id.database);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, DatabaseActivity.class);
				MainActivity.this.startActivity(i);
			}
		});


		b = (Button) findViewById(R.id.cameraCropExternal);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CameraCropSeperateActivity.class);
				MainActivity.this.startActivity(i);
			}
		});
		

		b = (Button) findViewById(R.id.cameraCropInternal);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CameraCropInternalActivity.class);
				MainActivity.this.startActivity(i);
			}
		});
		

		b = (Button) findViewById(R.id.btChat);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, BluetoothChatActivity.class);
				MainActivity.this.startActivity(i);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
