package com.technext.cwc.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.android.volley.VolleyError;
import com.gc.materialdesign.views.ButtonFlat;
import com.technext.cwc.R;
import com.technext.cwc.ViewPagerActivity;
import com.technext.cwc.adapter.ProductListAdapter;
import com.technext.cwc.database.model.Categories;
import com.technext.cwc.database.model.Category;
import com.technext.cwc.database.model.Location;
import com.technext.cwc.database.model.Locations;
import com.technext.cwc.database.model.Product;
import com.technext.cwc.database.model.Products;
import com.technext.cwc.dialog.SplashProgressDialog;
import com.technext.cwc.http.Client;
import com.technext.cwc.listener.EndlessProductScrollListener;
import com.technext.cwc.listener.VolleyResponseHandler;


import com.technext.cwc.utils.URLUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class ProductListFragment extends Fragment implements OnClickListener, OnEditorActionListener{

	private ListView productListVew;
	private ProductListAdapter productListAdapter;
	private List<Product> products = new ArrayList<Product>();
	private View footerView;
	private EditText search_box;
	private Spinner filter_location, filter_category, sort_spinner;
	private ButtonFlat filter_price;
	private AlertDialog.Builder alertDialog;
	
	private String[] list_location= {"Andra Pradesh","Arunachal Pradesh","Assam","Bihar","Haryana","Himachal Pradesh", "Jammu and Kashmir", "Jharkhand","Karnataka", "Kerala","Tamil Nadu"};
	private String[] list_category= {"Andra Pradesh","Arunachal Pradesh","Assam","Bihar","Haryana","Himachal Pradesh", "Jammu and Kashmir", "Jharkhand","Karnataka", "Kerala","Tamil Nadu"};
	private String[] list_sort= {"Most Recent", "Most Viewed", "Price Low to High", "Price High to Low"};
	
	ArrayAdapter<String> adapter_location;
	ArrayAdapter<String> adapter_category;
	ArrayAdapter<String> adapter_sort;
	
	private SplashProgressDialog progress;
	private EndlessProductScrollListener endlessScroolListener;
	
	public ProductListFragment(){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_product_list, container,
				false);
		
		progress = new SplashProgressDialog(getActivity());
		
		productListVew = (ListView) rootView.findViewById(R.id.productListView);
		productListAdapter = new ProductListAdapter(getActivity(), products);
		footerView = ((LayoutInflater) this.getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.list_loading_item, null);
		productListVew.addFooterView(footerView);
		footerView.setVisibility(View.GONE); // default invisible
		endlessScroolListener = new EndlessProductScrollListener(productListVew,footerView,
				productListAdapter, getActivity());
		productListVew.setOnScrollListener(endlessScroolListener);
		productListVew.setAdapter(productListAdapter);
		
		search_box = (EditText) rootView.findViewById(R.id.search_box); 
		search_box.setOnEditorActionListener(this);
		
		filter_location = (Spinner) rootView.findViewById(R.id.filter_location);
		filter_price = (ButtonFlat) rootView.findViewById(R.id.filter_price);
		filter_category = (Spinner) rootView.findViewById(R.id.filter_category);
		sort_spinner = (Spinner) rootView.findViewById(R.id.sort_spinner);
		
		adapter_location = new ArrayAdapter<String>(getActivity(),  R.layout.spinner_item, list_location);
		adapter_category = new ArrayAdapter<String>(getActivity(),  R.layout.spinner_item, list_category);
		adapter_sort = new ArrayAdapter<String>(getActivity(),  R.layout.spinner_item, list_sort);
		
		filter_location.setAdapter(adapter_location);
		filter_category.setAdapter(adapter_category);
		sort_spinner.setAdapter(adapter_sort);
		sort_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				progress.show();
				filterBy(filter_location.getSelectedItem().toString(), 
						filter_category.getSelectedItem().toString(),
						sort_spinner.getSelectedItem().toString(), null, null, new VolleyResponseHandler<Products>() {

							@Override
							public void onSuccess(Products response) {
								progress.dismiss();
								if(response != null){
									products = response.getProducts();
									//productListAdapter.notifyDataSetChanged();
									productListAdapter.setItems(products);
									endlessScroolListener.setPreviousTotal(0);
									productListAdapter.notifyDataSetChanged();
									
								}
								
							}

							@Override
							public void onError(VolleyError error) {
								// TODO Auto-generated method stub
								progress.dismiss();
								
							}
						});
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Client.volleyRawPost(URLUtils.URL_LOCATIONS, null, Locations.class, null, locationResponseHandler);
		Client.volleyRawPost(URLUtils.URL_CATEGORIES, null, Categories.class, null, categoryResponseHandler);	
		
		filter_price.setOnClickListener(this);
		
		Client.volleyRawPost(URLUtils.URL_PRODUCTS, null, Products.class, null, productsResponseHandler);
		
		return rootView;
	}

	private void volleyReqWithPriceFilter(String min_s, String max_s) {
		if(min_s == null || min_s.equals("")){
			min_s = "0";
		}
		if(max_s == null || max_s.equals("")){
			max_s = "0";
		}
		
		int min = Integer.parseInt(min_s);
		int max = Integer.parseInt(max_s);
		
		progress.show();
		
		filterBy(filter_location.getSelectedItem().toString(), 
				filter_category.getSelectedItem().toString(),
				sort_spinner.getSelectedItem().toString(), Double.valueOf(min), Double.valueOf(max),new VolleyResponseHandler<Products>() {

					@Override
					public void onSuccess(Products response) {
						progress.hide();
						if(response != null){
							products = response.getProducts();
							
							productListAdapter.setItems(products);
							endlessScroolListener.setPreviousTotal(0);
							productListAdapter.notifyDataSetChanged();
							
						}
						
					}

					@Override
					public void onError(VolleyError error) {
						// 
						progress.hide();
						
					}
				});
		
		Toast.makeText(getActivity(), min_s+" "+max_s, Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	VolleyResponseHandler<Categories> categoryResponseHandler = new VolleyResponseHandler<Categories>() {

		@Override
		public void onSuccess(Categories response) {
			if(response != null){
				List<Category> categories = response.getCategories();
				String[] categoriesArray = new String[categories.size()];
				
				Iterator<Category> iter = categories.iterator();
				int i = 0;
				while(iter.hasNext()){
					Category category = iter.next();
					categoriesArray[i] = category.name;
					i++;
				}
				
				list_category = categoriesArray;
				filter_category.setAdapter(new ArrayAdapter<String>(getActivity(),  R.layout.spinner_item, list_category));
				filter_category.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						
						progress.show();
						filterBy(filter_location.getSelectedItem().toString(), 
								filter_category.getSelectedItem().toString(),
								sort_spinner.getSelectedItem().toString(), null, null, new VolleyResponseHandler<Products>() {

									@Override
									public void onSuccess(Products response) {
										progress.hide();
										if(response != null){
											products = response.getProducts();
											
											productListAdapter.setItems(products);
											endlessScroolListener.setPreviousTotal(0);
											productListAdapter.notifyDataSetChanged();
											
										}
										
									}

									@Override
									public void onError(VolleyError error) {
										// TODO Auto-generated method stub
										
									}
								});
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						
						
					}
				});

			}
		}

		@Override
		public void onError(VolleyError error) {
			// TODO Auto-generated method stub
			
		}
	};
	
	VolleyResponseHandler<Products> productsResponseHandler = new VolleyResponseHandler<Products>() {

		@Override
		public void onSuccess(Products response) {
			if(response != null){
				products = response.getProducts();
				//productListAdapter.notifyDataSetChanged();
				productListAdapter.setItems(products);
				endlessScroolListener.setPreviousTotal(0);
				productListAdapter.notifyDataSetChanged();
				
			}
			//Log.e("product list size", "-->"+products.size());
		}

		@Override
		public void onError(VolleyError error) {
			Toast.makeText(getActivity(), "error-> "+ error.getMessage() , Toast.LENGTH_SHORT).show();
			
		}
	};
	
	
	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.filter_price:
			// Creating alert Dialog with one Button
	        alertDialog = new AlertDialog.Builder(getActivity());

	        LayoutInflater li = LayoutInflater.from(getActivity());
	        LinearLayout price_min_max_layout = (LinearLayout) li.inflate(R.layout.price_min_max, null);
	        final EditText price_min = (EditText) price_min_max_layout.findViewById(R.id.price_min_edittext);
	        final EditText price_max = (EditText) price_min_max_layout.findViewById(R.id.price_max_edittext);
			
	        // Setting Dialog Title
	        alertDialog.setTitle("Price");
	        alertDialog.setView(price_min_max_layout);
	        alertDialog.setCancelable(true);

	        // Setting Positive "Yes" Button
	        alertDialog.setPositiveButton("OK",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,int which) {
	                        volleyReqWithPriceFilter(price_min.getText().toString(), price_max.getText().toString());
	                    }
	                });
	        // Setting Negative "NO" Button
	        alertDialog.setNegativeButton("Cancel",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                        // Write your code here to execute after dialog
	                        dialog.cancel();
	                    }
	                });
			alertDialog.show();
			break;
		}
	}
	
	
	
	
	VolleyResponseHandler<Locations> locationResponseHandler = new VolleyResponseHandler<Locations>() {
		
		@Override
		public void onSuccess(Locations response) {
			if(response != null){
				
				List<Location> locations = response.getLocations();
				Log.e("size", "-->"+locations.size());
				String[] locationList = new String[locations.size()];
				
				Iterator<Location> iter = locations.iterator();
				int i = 0;
				while(iter.hasNext()){
					Location location = iter.next();
					locationList[i] = location.getName();
					i++;
				}
				list_location = locationList;
				filter_location.setAdapter(new ArrayAdapter<String>(getActivity(),  R.layout.spinner_item, list_location));
				filter_location.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						progress.show();
						filterBy(filter_location.getSelectedItem().toString(), 
								filter_category.getSelectedItem().toString(),
								sort_spinner.getSelectedItem().toString(), null, null, new VolleyResponseHandler<Products>() {

									@Override
									public void onSuccess(Products response) {
										progress.hide();
										if(response != null){
											products = response.getProducts();
											
											productListAdapter.setItems(products);
											endlessScroolListener.setPreviousTotal(0);
											productListAdapter.notifyDataSetChanged();
											
										}
										
									}

									@Override
									public void onError(VolleyError error) {
										progress.hide();
										
									}
								});
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			
		}
		
		@Override
		public void onError(VolleyError error) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private void filterBy(String location, String category, String criteria, Double minPrice, Double maxPrice, VolleyResponseHandler<Products> handler){
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(URLUtils.PARAM_LOCATION, location);
		params.put(URLUtils.PARAM_CATEORY, category);
		params.put(URLUtils.PARAM_CRITERIA, criteria);
		
		if(minPrice != null ){
			params.put(URLUtils.PARAM_MIN_PRICE, ""+minPrice.toString());
		}
		if(maxPrice != null){
			params.put(URLUtils.PARAM_MAX_PRICE, ""+maxPrice.toString());
		}		
		
		Client.volleyRawPost(URLUtils.URL_PRODUCTS, null, Products.class, null, handler);
	}



	

	

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(), "sfjhsdjf", Toast.LENGTH_SHORT).show();
		String searchText = search_box.getText().toString();
		if(searchText != null && !searchText.equalsIgnoreCase("")){
			HashMap<String,String> params = new HashMap<String, String>();
			params.put(URLUtils.PARAM_SEARCH, searchText);
			
			progress.show();
			Client.volleyRawPost(URLUtils.URL__SEARCH_PRODUCTS, params, Products.class, null, new VolleyResponseHandler<Products>() {

				@Override
				public void onSuccess(Products response) {
					progress.hide();
					if(response != null){
						products = response.getProducts();
						//productListAdapter.notifyDataSetChanged();
						productListAdapter.setItems(products);
						endlessScroolListener.setPreviousTotal(0);
						productListAdapter.notifyDataSetChanged();
						
					}
					
				}

				@Override
				public void onError(VolleyError error) {
					// TODO Auto-generated method stub
					progress.hide();
					
				}
			});

		}
				
		return false;
	}
}
