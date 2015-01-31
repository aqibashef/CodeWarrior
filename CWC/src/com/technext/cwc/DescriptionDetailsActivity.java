package com.technext.cwc;

import com.gc.materialdesign.views.LayoutRipple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class DescriptionDetailsActivity extends ActionBarActivity {
	private String title, desc, fixed_or_negotiable, sub_type1, sub_type2, sub_type3, sub_type4;
	private double price;
	
	private String category;
	
	private EditText edit_title, edit_desc, edit_price, edit_sub_type1, edit_sub_type2, edit_sub_type3, edit_sub_type4;
	
	public static final String KEY_TITLE = "title";
	public static final String KEY_DESC = "desc";
	public static final String KEY_PRICE = "price";
	
	private LayoutRipple submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description_details_activity_layout);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
		toolbar.setTitle("Give Product Details");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        
        edit_title = (EditText) findViewById(R.id.description_title);
        edit_desc = (EditText) findViewById(R.id.description_desc);
        edit_sub_type1 = (EditText) findViewById(R.id.description_dyn_1);
        edit_sub_type2 = (EditText) findViewById(R.id.description_dyn_2);
        edit_sub_type3 = (EditText) findViewById(R.id.description_dyn_3);
        edit_sub_type4 = (EditText) findViewById(R.id.description_dyn_4);
        edit_price = (EditText) findViewById(R.id.description_price);
        
        submit = (LayoutRipple) findViewById(R.id.desc_detail_submint);
        
        Intent intent = getIntent();
        
        category = intent.getStringExtra(CategoryActivity.CATEGORY_KEY);
        
        if(category != null){
        	if(category.equalsIgnoreCase("Jobs")){
        		edit_sub_type4.setVisibility(View.GONE);
        	}
        	if(category.equalsIgnoreCase("Real Estates")){
        		
        	}
        	
        	if(category.equalsIgnoreCase("For Sale")){
        		edit_sub_type4.setVisibility(View.GONE);
        		edit_sub_type3.setVisibility(View.GONE);
        	}
        	if(category.equalsIgnoreCase("Items Wanted")){
        		edit_sub_type4.setVisibility(View.GONE);
        		edit_sub_type3.setVisibility(View.GONE);
        		edit_sub_type2.setVisibility(View.GONE);
        	}
        	if(category.equalsIgnoreCase("Services")){
        		edit_sub_type4.setVisibility(View.GONE);
        		edit_sub_type3.setVisibility(View.GONE);
        		edit_sub_type2.setVisibility(View.GONE);
        	}
        }
        
        submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				title = edit_title.getText() != null ? edit_title.getText().toString() : "";
				price = Double.parseDouble(edit_price.getText() != null ? edit_price.getText().toString() : "0");
				desc = edit_title.getText() != null ? edit_title.getText().toString() : "";
				
				Intent intent = new Intent();
				intent.putExtra(KEY_TITLE, title);
				intent.putExtra(KEY_PRICE, price);
				intent.putExtra(KEY_DESC, desc);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
        
	}
}
