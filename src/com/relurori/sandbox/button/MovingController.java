package com.relurori.sandbox.button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MovingController {

    private Rect mRectTemp = new Rect();
    private final int[] mCoordinatesTemp = new int[2];
    
    private Context mContext = null;
    private InputMethodManager mInputMethodManager = null;
    private MovingListener mListener;
    private IBinder mWindowToken;
    
	public static final int ACTION_MOVE = 0;

	public MovingController(Context c) {
		mContext = c;
	}
	public void startMoving(MovingLayer movingLayer, View v, Object info,
			int action) {
		
		Bitmap b = Misc.getViewBitmap(v);
		assert(b != null);
		
		int[] coord = mCoordinatesTemp;
		v.getLocationInWindow(coord);
		
		startMoving(b, coord[0], coord[1], 0, 0, movingLayer, info, action);
		b.recycle();
		
		if (action == ACTION_MOVE) {
			v.setVisibility(View.GONE);
		}
	}

	private void startMoving(Bitmap b, int i, int j, int k, int l,
			MovingLayer movingLayer, Object info, int action) {
		
		if (mInputMethodManager == null) {
			mInputMethodManager = (InputMethodManager)
					mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		}
		mInputMethodManager.hideSoftInputFromWindow(mWindowToken, 0);
		
		if (mListener != null) {
			mListener.onDragStart(movingLayer, info, action);
		}
		
		
	}


	
}
