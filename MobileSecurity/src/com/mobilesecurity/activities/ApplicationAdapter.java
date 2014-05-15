package com.mobilesecurity.activities;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
private List<ApplicationInfo> appsList = null;
private Context context;
private PackageManager packageManager;
private ArrayList<Boolean> checkList = new ArrayList<Boolean>();


public ApplicationAdapter(Context context, int textViewResourceId,
        List<ApplicationInfo> appsList) {
    super(context, textViewResourceId, appsList);
    this.context = context;
    this.appsList = appsList;
    packageManager = context.getPackageManager();

    for (int i = 0; i < appsList.size(); i++) {
        checkList.add(false);
    }
}

public void checkAllFunction(){
	checkList.clear();
	for (int i = 0; i < appsList.size(); i++) {
        checkList.add(true);
    }
	notifyDataSetChanged();
	Log.d("MYYAG", ""+checkList.toString());
	
}

public void clearAllFunction(){
	checkList.clear();
	for (int i = 0; i < appsList.size(); i++) {
        checkList.add(false);
    }
	notifyDataSetChanged();
	Log.d("MYYAG", ""+checkList.toString());
	
}

@Override
public int getCount() {
    return ((null != appsList) ? appsList.size() : 0);
}

@Override
public ApplicationInfo getItem(int position) {
    return ((null != appsList) ? appsList.get(position) : null);
}

@Override
public long getItemId(int position) {
    return position;
}

@Override
public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    if (null == view) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.appunderprotection_row, null);
    }

    ApplicationInfo data = appsList.get(position);
    if (null != data) {
        TextView appName = (TextView) view.findViewById(R.id.app_name);
        //TextView packageName = (TextView) view.findViewById(R.id.app_paackage);
        ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_app);
        checkBox.setTag(Integer.valueOf(position)); // set the tag so we can identify the correct row in the listener
        checkBox.setChecked(checkList.get(position)); // set the status as we stored it 
        checkBox.setOnCheckedChangeListener(mListener); // set the listener

        appName.setText(data.loadLabel(packageManager));
       // packageName.setText(data.packageName);
        iconview.setImageDrawable(data.loadIcon(packageManager));
    }
    return view;
}

OnCheckedChangeListener mListener = new OnCheckedChangeListener() {

     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {   
         checkList.set((Integer)buttonView.getTag(),isChecked); // get the tag so we know the row and store the status 
     }
};
}