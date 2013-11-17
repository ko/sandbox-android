package com.relurori.sandbox.preferences;

import com.relurori.sandbox.R;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.util.Log;

public class DynamicPreferenceActivity extends PreferenceActivity {
	
	private static final String TAG = DynamicPreferenceActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);

		PreferenceCategory targetCategory = (PreferenceCategory) findPreference("dialog_based_preferences_key");
		// create one check box for each setting you need
		CheckBoxPreference checkBoxPreference = new CheckBoxPreference(this);
		// make sure each key is unique
		checkBoxPreference.setKey("keyName");
		checkBoxPreference.setChecked(true);

		targetCategory.addPreference(checkBoxPreference);

		
		ListPreference listPreference = new ListPreference(this);
		CharSequence[] charArray = new CharSequence[3];
		charArray[0] = "hello";
		charArray[1] = "array";
		charArray[2] = "world";
		listPreference.setEntryValues(charArray);
		listPreference.setTitle("title~!");
		CharSequence[] charArray2 = new CharSequence[3];
		charArray2[0] = "abc";
		charArray2[1] = "def";
		charArray2[2] = "ghi";
		listPreference.setEntries(charArray2);
		listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				Log.d(TAG,"newValue=" + newValue);
				return false;
			}
		});
		
		targetCategory.addPreference(listPreference);
	}
}
