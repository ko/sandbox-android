package com.relurori.sandbox.button;

import com.relurori.sandbox.R;
import com.relurori.sandbox.R.layout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MovingActivity extends Activity implements OnLongClickListener, OnTouchListener, OnDragListener {

	private static final String TAG = MovingActivity.class.getSimpleName();
	
	private RelativeLayout mRelativeLayout = null;
	private MovingButton b;
	
	private float deltaX, deltaY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movingbuttons);
		
		Intent i = getIntent();
		String key = i.getStringExtra("genericKey");

		
		mRelativeLayout = (RelativeLayout)findViewById(R.id.relLayout);
		
		onCreateAddButtons();
	}

	private void onCreateAddButtons() {
		b = new MovingButton(getBaseContext());
		b.setText("New button");
		b.setOnLongClickListener(this);
		b.setOnTouchListener(this);
		b.setOnDragListener(this);

		b.setDimensions(500, 100);
		
		RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(500, 100);
		lParams.leftMargin = 100;
		lParams.topMargin = 100;
		lParams.bottomMargin = 50;
		lParams.rightMargin = 50;
		b.setLayoutParams(lParams);
		
		mRelativeLayout.addView(b);
	}

	@Override
	public boolean onLongClick(View v) {
		if (v.isInTouchMode() == false) {
			Log.d(TAG,"onLongClick|not in touch mode");
			return false;
		}
		
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			((MovingButton)v).setDownLocation(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_MOVE:

			LayoutParams lparams = new RelativeLayout.LayoutParams((int)((MovingButton)v).getDimensionsWidth(), (int)((MovingButton)v).getDimensionsHeight());
			lparams.leftMargin = (int) (v.getLeft() + (event.getX() - ((MovingButton)v).getDownX()));
			lparams.topMargin = (int) (v.getTop() + (event.getY() - ((MovingButton)v).getDownY()));
			v.setLayoutParams(lparams);
			
            break;
		case MotionEvent.ACTION_UP:
			b = (MovingButton)v;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
		case MotionEvent.ACTION_POINTER_UP:
			break;
		}
		return true;
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		
		return false;
	}



}
