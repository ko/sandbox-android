package com.relurori.sandbox.bluetooth;

import java.util.Set;

import com.relurori.sandbox.R;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class BluetoothChatActivity extends Activity {

	private final String TAG = BluetoothChatActivity.class.getSimpleName();
	private static final int REQUEST_ENABLE_BT = 0;
	BluetoothAdapter mBtAdapter;
	
	ArrayAdapter mArrayAdapter;
	
	/*
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			}
		}
		
	};
	IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	*/
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_bt_chat);
		
		
		getBluetoothAdapter();
		enableBluetooth();
		//queryPairedDevices();
		//discoverDevices();
		
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
	
	private void queryPairedDevices() {
		Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
		// If there are paired devices
		if (pairedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        // Add the name and address to an array adapter to show in a ListView
		        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		    }
		}
	}

	/*
	private void discoverDevices() {
		registerReceiver(mReceiver, filter); // unregister onDestroy
	}
	*/
	
	private void connectAsServer() {
		
	}
}
