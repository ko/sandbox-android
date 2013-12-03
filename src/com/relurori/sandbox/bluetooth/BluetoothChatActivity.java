package com.relurori.sandbox.bluetooth;

import com.relurori.sandbox.R;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BluetoothChatActivity extends Activity {

	private final String TAG = BluetoothChatActivity.class.getSimpleName();
	private static final int REQUEST_ENABLE_BT = 0;
	BluetoothAdapter mBtAdapter;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_bt_chat);
		
		getBluetoothAdapter();
		enableBluetooth();
	}

	private void getBluetoothAdapter() {
		mBtAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBtAdapter == null) {
			// Device no support the BT
			
		}
	}
	

	private void enableBluetooth() {
		if (mBtAdapter.isEnabled() == false) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
	}
	
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case RESULT_OK:
			Log.d(TAG,"onActivityResult|RESULT_OK");
			handleRequest(requestCode);
			break;
		case RESULT_CANCELED:
			Log.d(TAG,"onActivityResult|RESULT_CANCELED");
			break;
		}
	}

	private void handleRequest(int requestCode) {
		switch (requestCode) {
		case REQUEST_ENABLE_BT:
			Log.d(TAG,"handleRequest|REQUEST_ENABLE_BT");
			
			break;
		}
	}
	
}
