package com.relurori.sandbox.button;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

public class Misc {

	private static final String TAG = Misc.class.getSimpleName();
	
	public static Bitmap getViewBitmap(View v) {
		v.clearFocus();
		v.setPressed(false);
		
		boolean willCache = v.willNotCacheDrawing();
		v.setWillNotCacheDrawing(false);
		
		int color = v.getDrawingCacheBackgroundColor();
		v.setDrawingCacheBackgroundColor(0);
		
		if (color != 0) {
			v.destroyDrawingCache();
		}
		v.buildDrawingCache();
		
		Bitmap cacheBitmap = v.getDrawingCache();
		if (cacheBitmap == null) {
			Log.d(TAG, "cacheBitmap DNE for " + v);
			return null;
		}
		
		Bitmap b = Bitmap.createBitmap(cacheBitmap);
		
		// Undo it all!
		v.destroyDrawingCache();
		v.setWillNotCacheDrawing(willCache);
		v.setDrawingCacheBackgroundColor(color);
		
		return b;
	}
}
