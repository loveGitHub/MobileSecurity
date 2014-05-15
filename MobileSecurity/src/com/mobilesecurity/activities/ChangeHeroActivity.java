package com.mobilesecurity.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChangeHeroActivity extends Activity{
	
	Button change_hero;
	EditText change_hero_name,change_hero_number;
	private final int PICK_CONTACT=0;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_change_hero);
	        initialization();
	        addOnListner();
	    }

	 public void initialization(){
		 
		 change_hero = (Button)findViewById(R.id.add_chnage_hero_btn);
		 change_hero_name = (EditText)findViewById(R.id.activity_change_hero_name);
		 change_hero_number=(EditText)findViewById(R.id.activity_change_hero_number);
	 }
	 
	 public void addOnListner(){
		 change_hero.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
			}
		});
		 
	 }
	 
	 @Override
		public void onActivityResult(int reqCode, int resultCode, Intent data) {
		  super.onActivityResult(reqCode, resultCode, data);

		  switch (reqCode) {
		    case (PICK_CONTACT) :
		      if (resultCode == Activity.RESULT_OK) {
		        Uri contactData = data.getData();
		        ContentResolver cr = getContentResolver();
		        Cursor c =  getContentResolver().query(contactData, null, null, null, null);
		        if (c.moveToFirst()) {
		        	String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
		        	String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		        	if(Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))>0){
		        		
		        		// get the phone number
			        	Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
			         		    			   ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
			         		    			   new String[]{id}, null);
	         	        while (pCur.moveToNext()) {
	         	        	  String phone = pCur.getString(
	         	        			 pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	         	        	 Log.d("phone is : " , phone);
	         	        	//Toast.makeText(RegisterActivity.this, "your phone : "+phone, Toast.LENGTH_LONG).show();
	         	        	change_hero_number.setText(phone);
	         	        	break;
	         	        } 
	         	        pCur.close();
		        	}
		          Log.d("Name is : ", name);
		          //Toast.makeText(RegisterActivity.this, "your name : "+name, Toast.LENGTH_LONG).show();
		          
		          // TODO Whatever you want to do with the selected contact name.
		          change_hero_name.setText(name);
		        }
		      }
		      break;
		  }
		}
	
}
