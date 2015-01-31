package com.technext.cwc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends Activity {
	private ListView listView;
	private String[] list_sort= {"Most Recent", "Most Viewed", "Price Low to High", "Price High to Low"};
	private String category;
	public static final String CATEGORY_KEY = "category_key";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_subcategory);
		
		listView = (ListView) findViewById(R.id.activity_category_subcategory_listview);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  R.layout.spinner_item, list_sort); 
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				category = adapter.getItem(position);
				Intent intent = new Intent(getApplicationContext(), SubCategoryActivity.class);
				intent.putExtra(CATEGORY_KEY, category);
				startActivityForResult(intent, 100);
			}
			
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK && requestCode == 100){
			data.putExtra(CATEGORY_KEY, category);
			setResult(RESULT_OK, data);
			Toast.makeText(getApplicationContext(), category, Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
