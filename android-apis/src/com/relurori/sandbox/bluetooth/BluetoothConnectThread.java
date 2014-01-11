package com.relurori.sandbox.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;

public class BluetoothConnectThread extends BluetoothChatActivity {

	public static final int MESSAGE_READ = 0;
	Handler mHandler;
	
	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;
		
		public ConnectedThread(BluetoothSocket socket) {
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;
			
			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mmInStream = tmpIn;
			mmOutStream = tmpOut;
		}
		
		public void run() {
			byte[] buffer = new byte[1024];
			int bytes;
			
			while (true) {
				try {
					bytes = mmInStream.read(buffer);
					mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
		
		public void write(byte[] bytes) {
			try {
				mmOutStream.write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
