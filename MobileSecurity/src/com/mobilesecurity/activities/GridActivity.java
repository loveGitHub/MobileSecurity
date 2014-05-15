package com.mobilesecurity.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import org.askerov.dynamicgid.DynamicGridView;

import com.mobilesecurity.model.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class GridActivity extends Activity {

	private DynamicGridView gridView;
	CustomGridViewAdapter customGridAdapter;

	ArrayList<Item> gridArray = new ArrayList<Item>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.home);
		Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.personal);

		gridArray.add(new Item(homeIcon, "Home"));
		gridArray.add(new Item(userIcon, "User"));
		

		gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
				gridArray);
		gridView.setAdapter(customGridAdapter);

		/*gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);
		gridView.setAdapter(new CheeseDynamicAdapter(this,
				new ArrayList<String>(Arrays.asList(Cheeses.sCheeseStrings)), 2));
*/
		
		gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				gridView.startEditMode();
				return false;
			}
		});

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(GridActivity.this,
						parent.getAdapter().getItem(position).toString(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onBackPressed() {
		if (gridView.isEditMode()) {
			gridView.stopEditMode();
		} else {
			super.onBackPressed();
		}
	}
}
