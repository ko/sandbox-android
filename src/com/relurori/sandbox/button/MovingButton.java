package com.relurori.sandbox.button;

import android.content.Context;
import android.widget.Button;

public class MovingButton extends Button {

	private static final String TAG = MovingButton.class.getSimpleName();
	
	private float marginTop = 0;
	private float marginLeft = 0;
	private float downX = 0;
	private float downY = 0;
	
	public MovingButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void setMargins(float top, float left) {
		marginTop = top;
		marginLeft = left;
	}
	
	public void setDownLocation(float x, float y) {
		downX = x;
		downY = y;
	}
	public float getDownX() {
		return downX;
	}
	public float getDownY() {
		return downY;
	}
}
