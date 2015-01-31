package com.technext.cwc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SubCategoryActivity extends Activity {
	private ListView listView;
	private String[] list_sort= {"Most Recent", "Most Viewed", "Price Low to High", "Price High to Low"};
	private String sub_category;
	public static final String SUB_CATEGORY_KEY = "sub_category_key";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_subcategory);
		
		Intent intent = getIntent();
		
		
		listView = (ListView) findViewById(R.id.activity_category_subcategory_listview);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  R.layout.spinner_item, list_sort); 
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				sub_category = adapter.getItem(position);
				Intent intent = new Intent(getApplicationContext(), SubCategoryActivity.class);
				intent.putExtra(SUB_CATEGORY_KEY, sub_category);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
}
