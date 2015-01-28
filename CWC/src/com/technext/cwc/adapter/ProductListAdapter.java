package com.technext.cwc.adapter;

import java.util.List;

import com.technext.cwc.R;
import com.technext.cwc.model.Product;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ProductListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context context;
	private List<Product> products;

	public ProductListAdapter(Context context, List<Product> products) {
		this.context = context;
		this.products = products;
		mInflater = (LayoutInflater) this.context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.product_list_item, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

	public void addItem(Product product) {
		products.add(product);
	}
	
	public void setItems(List<Product> products){
		this.products = products;
		notifyDataSetChanged();
	}

	private class ViewHolder {

	}

}
