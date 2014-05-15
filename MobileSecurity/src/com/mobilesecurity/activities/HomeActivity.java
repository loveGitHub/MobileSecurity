package com.mobilesecurity.activities;

import com.blahti.example.drag3.DragActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {

	Button mykeyStatus,changePass,changeHero,hiddenMode,appunderprotection,lock_screen_btn,lock_screen_4_img,lock_screen_2_img;
	private final int PICK_CONTACT=0;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialization();
        addOnlistner();
    }

    
    public void initialization(){
		 
    	mykeyStatus =(Button)findViewById(R.id.activity_myKey_status);
    	changePass =(Button)findViewById(R.id.activity_change_password);
    	changeHero =(Button)findViewById(R.id.activity_change_hero);
    	hiddenMode =(Button)findViewById(R.id.activity_hidden_app_mode);
    	appunderprotection = (Button)findViewById(R.id.activity_app_under_protection);

    	lock_screen_btn= (Button) findViewById(R.id.lock_screen_btn);
    	lock_screen_4_img = (Button) findViewById(R.id.lock_screen_4_img);
    	lock_screen_2_img = (Button) findViewById(R.id.lock_screen_2_img);
		 
	 }
    
    public void addOnlistner(){
    	
    	final Context context = this;
    	mykeyStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,MyKeyStatus.class);
				startActivity(intent);
			}
		});
    	changePass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,ChangePassActivity.class);
				startActivity(intent);
			}
		});
    	changeHero.setOnClickListener(new OnClickListener() {

    		@Override
    		public void onClick(View arg0) {
    		// TODO Auto-generated method stub
    			Intent intent = new Intent(context,ChangeHeroActivity.class);
				startActivity(intent);
    		}
    	});
    	hiddenMode.setOnClickListener(new OnClickListener() {

    		@Override
    		public void onClick(View arg0) {
    		// TODO Auto-generated method stub
    			Intent intent = new Intent(context,HiddenModeActivity.class);
				startActivity(intent);
    		}
    	});
    	
    	lock_screen_btn.setOnClickListener(new OnClickListener() {

    		@Override
    		public void onClick(View arg0) {
    		// TODO Auto-generated method stub
    			Intent intent = new Intent(context,LockScreenPreference.class);
				startActivity(intent);
    		}
    	});
    	
    	appunderprotection.setOnClickListener(new OnClickListener() {

    		@Override
    		public void onClick(View arg0) {
    		// TODO Auto-generated method stub
    			Intent intent = new Intent(context,AppUnderProtection.class);
				startActivity(intent);
    		}
    	});
    	
    	lock_screen_4_img.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,DragActivity.class);
				startActivity(intent);
			}
		});
    	
    	lock_screen_2_img.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,TwoScreenLock.class);
				startActivity(intent);
			}
		});

    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
