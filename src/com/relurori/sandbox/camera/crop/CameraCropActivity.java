package com.relurori.sandbox.camera.crop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CameraCropActivity extends Activity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		cropIt();
	}

	private void cropIt() {
		Intent intent = new Intent("com.android.camera.action.CROP");
	    intent.setType("image/*");
	    

	}
}
