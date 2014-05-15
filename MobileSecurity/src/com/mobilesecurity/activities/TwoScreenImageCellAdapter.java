package com.mobilesecurity.activities;

import com.blahti.example.drag3.ImageCell;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This class is used with a GridView object. It provides a set of ImageCell objects 
 * that support dragging and dropping.
 * 
 */

public class TwoScreenImageCellAdapter extends BaseAdapter 
{

// Constants
public static final int DEFAULT_NUM_IMAGES = 4;

/**
 */
// Variables
public ViewGroup mParentView = null;
private Context mContext;

public TwoScreenImageCellAdapter(Context c) 
{
    mContext = c;
}

/**
 */
// Methods

/**
 * getCount
 */

public int getCount() 
{
    Resources res = mContext.getResources();
    int numImages = res.getInteger (R.integer.num_images);    
    return numImages;
}

public Object getItem(int position) 
{
    return null;
}

public long getItemId(int position) {
    return position;
}

/**
 * getView
 * Return a view object for the grid.
 * 
 * @return ImageCell
 */
public View getView (int position, View convertView, ViewGroup parent) 
{
    mParentView = parent;

    // Toast.makeText(mContext, " Postion "+position, Toast.LENGTH_SHORT).show();
    
    ImageCell v = null;
    if (convertView == null) {
        // If it's not recycled, create a new ImageCell.
        v = new ImageCell (mContext);
        v.setLayoutParams(new GridView.LayoutParams(100,100));
        v.setScaleType(ImageView.ScaleType.CENTER_CROP);
        v.setPadding(1, 1, 1, 1);
        
        if(position==0){
        	v.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hello));
        }else if(position==1){
            v.setImageDrawable(mContext.getResources().getDrawable(R.drawable.image0));
        }

    } else {
        v = (ImageCell) convertView;
    }

    v.mCellNumber = position;
    v.mGrid = (GridView) mParentView;
    v.mEmpty = true;
//    v.setBackgroundResource (R.color.drop_target_enabled);
    //v.setBackgroundResource (R.drawable.image0);

    //v.mGrid.requestDisallowInterceptTouchEvent (true);

    //v.setImageResource (R.drawable.hello);

    // Set up to relay events to the activity.
    // The activity decides which events trigger drag operations.
    // Activities like the Android Launcher require a long click to get a drag operation started.
    v.setOnTouchListener ((View.OnTouchListener) mContext);
    v.setOnClickListener ((View.OnClickListener) mContext);
    v.setOnLongClickListener ((View.OnLongClickListener) mContext);

    return v;
}


} // end class
