package com.technext.cwc.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyListAdapter extends ArrayAdapter<String>{

	String[] items;
	
	public MyListAdapter(Context context,
			int textViewResourceId, String[] objects) {
		super(context, textViewResourceId, objects);
		this.items = objects;
		// TODO Auto-generated constructor stub
	}
	
	public void setItems(String[] items){
		this.items = items;
	}
	

}
