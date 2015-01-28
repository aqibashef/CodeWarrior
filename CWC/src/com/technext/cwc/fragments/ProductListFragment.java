package com.technext.cwc.fragments;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.technext.cwc.R;
import com.technext.cwc.adapter.ProductListAdapter;
import com.technext.cwc.http.Client;
import com.technext.cwc.listener.VolleyResponseHandler;
import com.technext.cwc.model.Product;
import com.technext.cwc.model.Products;
import com.technext.cwc.utils.URLUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ProductListFragment extends Fragment{

	private ListView productListVew;
	private ProductListAdapter productListAdapter;
	private List<Product> products = new ArrayList<Product>();
	
	public ProductListFragment(){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_product_list, container,
				false);
		
		productListVew = (ListView) rootView.findViewById(R.id.productListView);
		productListAdapter = new ProductListAdapter(getActivity(), products);
		productListVew.setAdapter(productListAdapter);
		
		Client.volleyRawPost(URLUtils.URL_PRODUCTS, null, Products.class, null, productsResponseHandler);
		
		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	VolleyResponseHandler<Products> productsResponseHandler = new VolleyResponseHandler<Products>() {

		@Override
		public void onSuccess(Products response) {
			if(response != null){
				products = response.getProducts();
				//productListAdapter.notifyDataSetChanged();
				productListAdapter.setItems(products);
				
			}
			Log.e("product list size", "-->"+products.size());
		}

		@Override
		public void onError(VolleyError error) {
			// TODO Auto-generated method stub
			
		}
	};
	
}
