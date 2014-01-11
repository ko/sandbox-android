package com.relurori.sandbox.camera.crop.external;

import java.io.File;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CameraCropSeperateActivity extends Activity {

	private static final String TAG = CameraCropSeperateActivity.class.getSimpleName();
	
	final int CAMERA_CAPTURE = 0x0001;
	final int REQUEST_CODE_CROP = 0x1001;
	private Uri uri;
	private Uri saveUri;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		sendCamera();
	}

	private void sendCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, CAMERA_CAPTURE);
	}

	private void processFromCamera(Intent data) {

		uri = data.getData();

		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can only read the media
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			// Something else is wrong. It may be one of many other states, but
			// all we need
			// to know is we can neither read nor write
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		
		if (mExternalStorageWriteable) {
			File file = new File(getExternalFilesDir(null), "temp.png");
			saveUri = Uri.fromFile(file);
		} else {
			Log.d(TAG, "External storage not writeable");
		}
		
		Log.d(TAG,"saveUri=" + saveUri);
		
		cropImage();
	}

	private void cropImage() {
		try {
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, "image/*");
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("scale", "true");
			intent.putExtra("scaleUpIfNeeded", true);
			intent.putExtra("return-data", false);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, saveUri);
			startActivityForResult(intent, REQUEST_CODE_CROP);
		} catch (ActivityNotFoundException e) {
			String errorMessage = "Whoops - your device doesn't support the crop action!";
			Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.show();
			e.printStackTrace();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case CAMERA_CAPTURE:
				processFromCamera(data);
				break;
			default:
				break;
			}
		}
	}

}
