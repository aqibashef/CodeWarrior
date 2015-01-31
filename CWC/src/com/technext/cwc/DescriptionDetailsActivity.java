package com.technext.cwc;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class DescriptionDetailsActivity extends ActionBarActivity {
	private String tilte, desc, fixed_or_negotiable, sub_type1, sub_type2, sub_type3, sub_type4;
	private double price;
	
	private EditText edit_title, edit_desc, edit_sub_type1, edit_sub_type2, edit_sub_type3, edit_sub_type4;
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
	}
}
