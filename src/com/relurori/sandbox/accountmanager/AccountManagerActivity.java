package com.relurori.sandbox.accountmanager;

import java.io.IOException;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.relurori.sandbox.R;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class AccountManagerActivity extends Activity {

	private static final String TAG = AccountManagerActivity.class.getSimpleName();
	
	private AccountManager mAccountManager;
	String mEmail;
	String mScope = "https://spreadsheets.google.com/feeds";
	String token;
	private AccountManagerActivity mActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oauth2);
		
		mActivity = this;
		
		mEmail = getAccountNames()[0];
		Log.d(TAG,"mEmail=" + mEmail);
		new Task().execute();
	}



	private String[] getAccountNames() {
	    mAccountManager = AccountManager.get(this);
	    Account[] accounts = mAccountManager.getAccountsByType(
	            GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
	    String[] names = new String[accounts.length];
	    for (int i = 0; i < names.length; i++) {
	        names[i] = accounts[i].name;
	    }
	    return names;

	}
	
	class Task extends AsyncTask<Void,Void,Void> {

		private final String TAG = Task.class.getSimpleName();
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				token = GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
			} catch (UserRecoverableAuthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GoogleAuthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Log.d(TAG,"token=" + token);
			return null;
		}
		
	}

}
