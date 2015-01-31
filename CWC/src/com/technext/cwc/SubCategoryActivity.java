package com.technext.cwc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SubCategoryActivity extends ActionBarActivity {
	private ListView listView;
	private String[] list_sort= {"Most Recent", "Most Viewed", "Price Low to High", "Price High to Low"};
	private String sub_category;
	public static final String SUB_CATEGORY_KEY = "sub_category_key";
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_subcategory);
		
		intent = getIntent();
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
		toolbar.setTitle("Category");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
		
		listView = (ListView) findViewById(R.id.activity_category_subcategory_listview);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  R.layout.spinner_item, list_sort); 
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				sub_category = adapter.getItem(position);
				intent = new Intent(getApplicationContext(), SubCategoryActivity.class);
				intent.putExtra(SUB_CATEGORY_KEY, sub_category);
				setResult(RESULT_OK, intent);
				Toast.makeText(getApplicationContext(), sub_category, Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}
}
