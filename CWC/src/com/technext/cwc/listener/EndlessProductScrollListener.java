package com.technext.cwc.listener;

import java.util.HashMap;
import java.util.Iterator;

import com.android.volley.VolleyError;

import com.technext.cwc.adapter.ProductListAdapter;
import com.technext.cwc.http.Client;
import com.technext.cwc.model.Product;
import com.technext.cwc.model.Products;
import com.technext.cwc.utils.URLUtils;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class EndlessProductScrollListener implements OnScrollListener{

	private ProductListAdapter productListAdapter;
	private Context context;
	private int threshold = 0;
	private int offset = 0;
	private int previousTotal = 0;
	public static final int LIMIT = 20;
	private boolean loading = true;
	private ListView listview;
	private View footerView;
	
	
	public EndlessProductScrollListener(ListView listview,View footerView,ProductListAdapter productListAdapter, Context context){
		this.productListAdapter = productListAdapter;
		this.context = context;
		this.listview = listview;
		this.footerView = footerView;
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if(scrollState == OnScrollListener.SCROLL_STATE_IDLE || scrollState == OnScrollListener.SCROLL_STATE_FLING){
			//AlertDialogUtils.show(context, "catched");
		}
		
		if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
           // BaseActivity.imageLoader.setPauseWork(true);
        } else {
        	//BaseActivity.imageLoader.setPauseWork(false);
		}
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
		if(loading){
			if(totalItemCount > previousTotal) {
				loading = false;
				previousTotal = totalItemCount;
				offset+=LIMIT;
			}
		}
		
		if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + threshold)) {
	        //show list loading footerview
			//footerView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_loading_item, null);
			//listview.addFooterView(footerView);
			footerView.setVisibility(View.VISIBLE);
			
			//params need : username, session_token
	      	//HashMap<String,String> params = new HashMap<String,String>();
	        
	      /*	Client.post(context, Client.URL_BEATS_RECENT_BEATS + LIMIT + "/" + offset, params, postRecentBeatHandler);*/
	      	Client.volleyRawPost(URLUtils.URL_PRODUCTS+"/"+ + LIMIT + "/" + offset, null, Products.class, null, responseHandler);
	      	
			loading = true;
		}
		
	}
	
	VolleyResponseHandler<Products> responseHandler = new VolleyResponseHandler<Products>() {
		
		@Override
		public void onSuccess(Products response) {
			if(response!= null){
				Iterator<Product> iter = response.getProducts().iterator();
				while(iter.hasNext()){
					productListAdapter.addItem(iter.next());
				}
				
				productListAdapter.notifyDataSetChanged();
			}
			footerView.setVisibility(View.GONE);
		}
		
		@Override
		public void onError(VolleyError error) {
			footerView.setVisibility(View.GONE);
			
		}
	};

}
