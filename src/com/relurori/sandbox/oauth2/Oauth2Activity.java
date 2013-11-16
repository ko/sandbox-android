package com.relurori.sandbox.oauth2;

import com.relurori.sandbox.R;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;

public class Oauth2Activity extends Activity {

	private AccountManager mAccountManager;
	Oauth2Activity mActivity;
	String mEmail;
	String mScope = "https://spreadsheets.google.com/feeds https://docs.google.com/feeds";
	String token;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oauth2);
		
			
	}

}
