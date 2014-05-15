package com.mobilesecurity.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{

	private Spinner spinner;
	private Cursor cursor1;
	private RegisterActivity self;
	Button addContacts;
	EditText heroName,heroNumber,myNumber;
	private final int PICK_CONTACT=0;
	private String[] phoneNumbers;
	private int phoneNumberCount;
	
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       self = this;
	       setContentView(R.layout.activity_register);
	       initialization();
	       addOnlistner();
	   }

	public void initialization(){
		addContacts = (Button)findViewById(R.id.add_hero_btn);
		heroName = (EditText)findViewById(R.id.activity_hero_name);
		heroNumber = (EditText)findViewById(R.id.activity_hero_number);
		myNumber = (EditText)findViewById(R.id.activity_login_my_number);
	}

	public void addOnlistner(){

	addContacts.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, PICK_CONTACT);
	}
	});

	}

	public int getPhoneNumberCount()
	{
		return phoneNumberCount;
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

	        		Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
	  		    			   ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
	  		    			   new String[]{id}, null);
	        		phoneNumberCount = pCur.getCount();
	        		phoneNumbers = new String[phoneNumberCount];
	        		int i = 0;
		        	while (pCur.moveToNext()) {
					   	String phone = pCur.getString(
					   			pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						phoneNumbers[i++] = phone;
		        	} 

	        		if(phoneNumberCount == 1)
	        		{
	     	        	String phone = phoneNumbers[0];
	     	        	heroNumber.setText(phone);
	     	        	heroNumber.setVisibility(View.VISIBLE);
	        		}
	        		else
	        		{
	     	        	heroNumber.setText("Hero Number");
	     	        	heroNumber.setVisibility(View.VISIBLE);
	     	        	heroNumber.setOnFocusChangeListener(new OnFocusChangeListener()
	     	        	{
	     	               @Override
	     	               public void onFocusChange(View v, boolean hasFocus) 
	     	               {
	     	            	   int i = 0;
	     	                   if (hasFocus == true)
	     	                   {
	     			        		AlertDialog.Builder builder = new AlertDialog.Builder(self);
	     			        		builder.setTitle("Select a Number");
	     			        		builder.setItems(phoneNumbers, new DialogInterface.OnClickListener() {
	     			        		    public void onClick(DialogInterface dialog, int item) {
	     			        		        String selected = phoneNumbers[item];
	     			        		        heroNumber.setText(selected);
	     			        		    }
	     			        		}).show();
	     	                   }
	     	               }
	     	        	});
	        		}
         	        pCur.close();
	        	}
	        	else {	
	        	}
	        	Log.d("Name is : ", name);
	        	
	        	//TODO Whatever you want to do with the selected contact name.
	        	heroName.setText(name);
	        	heroName.setVisibility(View.VISIBLE);
	        	myNumber.setVisibility(View.VISIBLE);
	        }
	      }
	      break;
	  }
	}

}
