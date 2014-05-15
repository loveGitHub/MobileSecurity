package com.mobilesecurity.activities;

import com.mobilesecurity.activities.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button register,forgotPass,login;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        addOnlistner();
    }

    
    public void initialization(){
		 
		 register =(Button)findViewById(R.id.activity_register);
		 forgotPass = (Button)findViewById(R.id.activity_forgot_pass);
		 login = (Button)findViewById(R.id.activity_login_btLogin);
		 
	 }
    
    public void addOnlistner(){
    	
    	final Context context = this;
    	initialization();
    	register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,RegisterActivity.class);
				startActivity(intent);
			}
		});
    	forgotPass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,ForgotPassActivity.class);
				startActivity(intent);
			}
		});
    	login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,HomeActivity.class);
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
