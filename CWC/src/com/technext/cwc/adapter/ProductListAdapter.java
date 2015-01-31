package com.technext.cwc.adapter;

import java.util.List;

import com.gc.materialdesign.views.LayoutRipple;
import com.squareup.picasso.Picasso;
import com.technext.cwc.R;
import com.technext.cwc.ViewPagerActivity;
import com.technext.cwc.database.model.Product;
import com.technext.cwc.http.Client;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductListAdapter extends BaseAdapter implements OnClickListener {

	private LayoutInflater mInflater;
	private Context context;
	private List<Product> products;
	public static final String SERVER_ID = "server_id";

	public ProductListAdapter(Context context, List<Product> products) {
		this.context = context;
		this.products = products;
		mInflater = (LayoutInflater) this.context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(products == null){
			return 0;
		}else{
			return products.size();
		}
	
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
		Product product = (Product) getItem(position);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.product_list_item, null);
			holder = new ViewHolder();
			
			holder.productListItemRippleLayout = (LayoutRipple) convertView.findViewById(R.id.productListItemRippleLayout);
			holder.productImage = (ImageView) convertView.findViewById(R.id.productImage);
			
			holder.productTitle = (TextView) convertView.findViewById(R.id.productTitle);
			holder.productLocation = (TextView) convertView.findViewById(R.id.productLocation);
			holder.productTime = (TextView) convertView.findViewById(R.id.productTime);
			holder.productPriceType = (TextView) convertView.findViewById(R.id.productPriceType);
			holder.productPrice = (TextView) convertView.findViewById(R.id.productPrice);
			holder.product = product;
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		String url = "";
		if(product.images!= null){
			url = product.images.get(0).getServer_url();
		}
		
		
		
		if(url == null || url.equalsIgnoreCase("")){
			Picasso.with(context).load(R.drawable.empty_photo)
			.placeholder(R.drawable.empty_photo)
			.error(R.drawable.empty_photo)
			.into(holder.productImage);
		}else{
			Picasso.with(context).load(url)
			.placeholder(R.drawable.empty_photo)
			.error(R.drawable.empty_photo)
			.into(holder.productImage);
		}

		/*Log.e("product title", product.getTitle());
		Log.e("product location", product.getLocation().getName());*/
		
		holder.productTitle.setText(product.getTitle());
		holder.productLocation.setText(product.getLocation().getName());
		holder.productTime.setText(product.getCreated_at());
		holder.productPriceType.setText(product.getPrice_type());
		holder.productPrice.setText(product.getPrice().toString()+"/=");
		
		convertView.setOnClickListener(ProductListAdapter.this);

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
		LayoutRipple productListItemRippleLayout;
		ImageView productImage;
		TextView productTitle;
		TextView productLocation;
		TextView productTime;
		TextView productPriceType;
		TextView productPrice;
		Product product;
	
	}

	@Override
	public void onClick(View v) {
		ViewHolder holder = (ViewHolder) v.getTag();
		Intent intent = new Intent(context, ViewPagerActivity.class);
		intent.putExtra(SERVER_ID, holder.product.getServer_id());
		context.startActivity(intent);
	}

}
