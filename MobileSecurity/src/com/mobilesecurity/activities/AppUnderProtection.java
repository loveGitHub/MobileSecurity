package com.mobilesecurity.activities;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AppUnderProtection extends ListActivity {
	
	private PackageManager packageManager = null;
	private List<ApplicationInfo> applist = null;
	private ApplicationAdapter listadaptor = null;
	
	Button select_all,clear_all;
	ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.app_under_protection_list);

	    packageManager = getPackageManager();
	    select_all = (Button)findViewById(R.id.select_all);
	    clear_all = (Button)findViewById(R.id.clear_all);
	    list = (ListView)findViewById(android.R.id.list);
	    

	    new LoadApplications().execute();
	    addOnListner();
	}
	
	public void addOnListner(){
		select_all.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				listadaptor.checkAllFunction();
				listadaptor.notifyDataSetChanged();
				//list.getAdapter().notifyDataSetChanged()
				
			}
		});
		clear_all.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				listadaptor.clearAllFunction();
				listadaptor.notifyDataSetChanged();
				//list.getAdapter().notifyDataSetChanged()
				
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);

	    ApplicationInfo app = applist.get(position);
	    try {
	        Intent intent = packageManager
	                .getLaunchIntentForPackage(app.packageName);

	        if (null != intent) {
	            startActivity(intent);
	        }
	    } catch (ActivityNotFoundException e) {
	        Toast.makeText(AppUnderProtection.this, e.getMessage(),
	                Toast.LENGTH_LONG).show();
	    } catch (Exception e) {
	        Toast.makeText(AppUnderProtection.this, e.getMessage(),
	                Toast.LENGTH_LONG).show();
	    }
	}

	private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
	    ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
	    for (ApplicationInfo info : list) {
	        try {
	            if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
	                applist.add(info);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return applist;
	}

	private class LoadApplications extends AsyncTask<Void, Void, Void> {
	    private ProgressDialog progress = null;

	    @Override
	    protected Void doInBackground(Void... params) {
	        applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
	        listadaptor = new ApplicationAdapter(AppUnderProtection.this,
	                R.layout.appunderprotection_row, applist);

	        return null;
	    }

	    @Override
	    protected void onCancelled() {
	        super.onCancelled();
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        setListAdapter(listadaptor);
	        progress.dismiss();
	        super.onPostExecute(result);
	    }

	    @Override
	    protected void onPreExecute() {
	        progress = ProgressDialog.show(AppUnderProtection.this, null,
	                "Loading application info...");
	        super.onPreExecute();
	    }

	    @Override
	    protected void onProgressUpdate(Void... values) {
	        super.onProgressUpdate(values);
	    }
	}

}
