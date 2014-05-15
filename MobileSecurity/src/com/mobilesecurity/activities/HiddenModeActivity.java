package com.mobilesecurity.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HiddenModeActivity extends Activity{
	
	Button activate_hidden_mode;
	TextView information1,information2;
	private static final String BULLET_SYMBOL = "&#8226;";
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_hidden);
	        initialization();
	        addOnListner();
	    }

	 public void initialization(){
		 activate_hidden_mode=(Button)findViewById(R.id.activity_hidden_mode_activate_btn);
		 information1 = (TextView)findViewById(R.id.activity_hidden_information1);
		 information2 = (TextView)findViewById(R.id.activity_hidden_information2);
	 }
	 
	 public void addOnListner(){
		 activate_hidden_mode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("Here I am", "There you are");
				String str1 = BULLET_SYMBOL +" Activates at next reboot.";
				String str2 = BULLET_SYMBOL +" Login to website to unhide app again.";
				information1.setText(Html.fromHtml(str1));
				information2.setText(Html.fromHtml(str2));
			}
		});
		 
	 }
	 
	 
	
}
