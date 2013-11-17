package com.relurori.sandbox.oauth2;

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
	
	String mEmail;
	String mScope = "https://spreadsheets.google.com/feeds";
	String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oauth2);

		Intent intent = new Intent(this, HelloActivity.class);
        intent.putExtra(HelloActivity.TYPE_KEY, HelloActivity.Type.FOREGROUND.name());
        Log.d(TAG,"start intent");
        startActivity(intent);
	}

	
}
