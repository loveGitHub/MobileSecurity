package com.mobilesecurity.activities;

import net.bgreco.DirectoryPicker;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LockScreenPreference extends Activity {

	Button folder_picker_btn;
	TextView chosen_folder;
	String c_folder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lock_screen_preference);

		initialization();
		addOnlistner();

		c_folder = getFromSP("ChosenFolder");

		if (!c_folder.equalsIgnoreCase("")) {
			chosen_folder.setText(c_folder);
		} else {
			chosen_folder.setText("Choose a Folder");
		}
	}

	private void initialization() {
		folder_picker_btn = (Button) findViewById(R.id.folder_picker_btn);
		chosen_folder = (TextView) findViewById(R.id.chosen_folder);
	}

	private void addOnlistner() {

		folder_picker_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LockScreenPreference.this,
						DirectoryPicker.class);
				// optionally set options here
				startActivityForResult(intent, DirectoryPicker.PICK_DIRECTORY);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == DirectoryPicker.PICK_DIRECTORY
				&& resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			String path = (String) extras.get(DirectoryPicker.CHOSEN_DIRECTORY);

			Toast.makeText(LockScreenPreference.this, "Path : " + path,
					Toast.LENGTH_LONG).show();

			storeInSP("ChosenFolder", "" + path);
			// do stuff with path
		}

		c_folder = getFromSP("ChosenFolder");

		if (!c_folder.equalsIgnoreCase("")) {
			chosen_folder.setText(c_folder);
		} else {
			chosen_folder.setText("Choose a Folder");
		}
	}

	public void storeInSP(String key, String val) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(key, val);
		editor.commit();
	}

	public String getFromSP(String key) {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String name = preferences.getString(key, "");
		if (!name.equalsIgnoreCase("")) {
			return name;
		}
		return "";
	}
}
