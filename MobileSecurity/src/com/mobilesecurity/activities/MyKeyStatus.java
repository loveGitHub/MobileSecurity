package com.mobilesecurity.activities;

import java.util.ArrayList;

import com.mobilesecurity.model.MKSModel;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.res.Resources;


public class MyKeyStatus extends Activity {

	ListView list;
	CustomListAdapter adapter;
	public  MyKeyStatus CustomListView = null;
	public  ArrayList<MKSModel> CustomListViewValuesArr = new ArrayList<MKSModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mykeystatus_list);
		
		CustomListView = this;
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();
		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.mks_list);
        
        /**************** Create Custom Adapter *********/
        adapter=new CustomListAdapter(CustomListView, CustomListViewValuesArr,res);
        list.setAdapter(adapter);
		
	}

	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	
		for (int i = 0; i < 5; i++) {
			
			final MKSModel sched = new MKSModel();
			    
			  /******* Firstly take data in model object ******/
			   sched.setMks_key("MKS_Key "+i);
			   sched.setMks_value("MKS_Value"+i);
			   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(sched);
		}
		
    }
    
    public void onItemClick(int mPosition)
    {
    	MKSModel tempValues = (MKSModel) CustomListViewValuesArr.get(mPosition);
    	
    	Toast.makeText(CustomListView, 
    			""+tempValues.getMks_key()+" : "+tempValues.getMks_value(), 
    			Toast.LENGTH_LONG)
    	.show();
    }
   

}
