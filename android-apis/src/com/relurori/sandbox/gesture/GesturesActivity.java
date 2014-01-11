package com.relurori.sandbox.gesture;

import com.relurori.sandbox.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GesturesActivity extends Activity {
	
	private static final String TAG = GesturesActivity.class.getSimpleName();
	
	private int screenX = 0;
	private int screenY = 0;
	
	private GestureUtil gesture = null;
	
	private Button buttonEast;
	private Button buttonWest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.gestures);
		
		Intent i = getIntent();
		String key = i.getStringExtra("genericKey");

		getScreenResolution();
		gesture = new GestureUtil(screenX, screenY);
		
		onCreateSetupButtons();
	}
	
	private void onCreateSetupButtons() {
		buttonEast = (Button) findViewById(R.id.buttonEast);
		buttonEast.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "button east", Toast.LENGTH_SHORT).show();
			}
		});
		
		buttonWest = (Button) findViewById(R.id.buttonWest);
		buttonWest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "button west", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void getScreenResolution() {
		WindowManager wm = (WindowManager) getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point point = new Point();
		display.getSize(point);
		screenX = point.x;
		screenY = point.y;
		Log.d(TAG,"ScreenX,ScreenY=" + screenX + "," + screenY);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){ 
		int action = MotionEventCompat.getActionMasked(event);

	    switch(action) {
	        case (MotionEvent.ACTION_DOWN) :
	        	gesture.setActionDown(event);
	            return true;
	            
	        case (MotionEvent.ACTION_MOVE) :
	            return true;
	        
	        case (MotionEvent.ACTION_UP) :
	        	if (gesture.getGestureDownNearEdgeState() == true) {
	        		gesture.setActionUp(event);
	        		
	        		Log.d(TAG,"swipe is=" + gesture.getSwipe());
	        		
	        		if (gesture.getSwipe() == gesture.SWIPE_RIGHT) {
	        			if (buttonEast.getVisibility() == View.VISIBLE)
	        				buttonEast.setVisibility(View.GONE);
	        			else 
	        				buttonEast.setVisibility(View.VISIBLE);
	        		}
	        	}
	            return true;
	        case (MotionEvent.ACTION_CANCEL) :
	            Log.d(TAG,"Action was CANCEL");
	            return true;
	        case (MotionEvent.ACTION_OUTSIDE) :
	            Log.d(TAG,"Movement occurred outside bounds " +
	                    "of current screen element");
	            return true;      
	        default : 
	            return super.onTouchEvent(event);
	    }      
	}


}
