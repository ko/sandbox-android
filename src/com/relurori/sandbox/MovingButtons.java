package com.relurori.sandbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;

public class MovingButtons extends Activity {

	private static final String TAG = MovingButtons.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movingbuttons);
		
		Intent i = getIntent();
		String key = i.getStringExtra("genericKey");
		
		onCreateAddButtons();
	}

	private void onCreateAddButtons() {
		Button b = new Button(getBaseContext());
		b.setText("New button");
		
		
	}

	OnTouchListener listener = new OnTouchListener() {

		float startX = 0f;
		float startY = 0f;
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				startX = event.getX();
				startY = event.getY();
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				
			}
			return true;
		}
		
	};
}
