package com.technext.cwc.fragments;

import java.util.HashMap;

import com.android.volley.VolleyError;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.LayoutRipple;
import com.gc.materialdesign.views.ScrollView;

import com.squareup.picasso.Picasso;
import com.technext.cwc.MainActivity;
import com.technext.cwc.R;
import com.technext.cwc.database.model.Product;
import com.technext.cwc.http.Client;
import com.technext.cwc.listener.VolleyResponseHandler;
import com.technext.cwc.share.SMSShare;
import com.technext.cwc.share.TMessaging;
import com.technext.cwc.utils.URLUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProductDetailsFragment extends Fragment implements OnClickListener {
	private final static String ARG_SECTION_NUMBER = "section_number";
	
	private ImageView product_details_image;
	private TextView product_details_time;
	private TextView product_details_price;
	private TextView product_details_location;
	private TextView product_details_type;
	private TextView product_details_product_name;
	private TextView product_details_description;
	private LayoutRipple product_details_call;
	private LayoutRipple product_details_email;
	private LayoutRipple product_details_sms;
	
	private Product product;
	
	public ProductDetailsFragment() {
		
	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ProductDetailsFragment newInstance(int sectionNumber) {
		ProductDetailsFragment fragment = new ProductDetailsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_product_details, container,
				false);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				MainActivity.screenWidth, MainActivity.screenHeight / 3);
		ImageView imageViewBg = (ImageView) rootView.findViewById(R.id.product_details_image);
		imageViewBg.setLayoutParams(params);
		
		String productimageUrl = "";
		if(product.images != null){
			productimageUrl = product.images.get(0).server_url;
		}
		if(productimageUrl == null){
			productimageUrl = "https://pbs.twimg.com/profile_images/1773564270/anonymous-logo-1.jpg";
		}
		
		Picasso.with(getActivity()).load(productimageUrl)
		.placeholder(R.drawable.empty_photo)
		.error(R.drawable.empty_photo)
		
		.into(imageViewBg);
		
		
		
		product_details_time = (TextView) rootView.findViewById(R.id.product_details_time);
		product_details_price = (TextView) rootView.findViewById(R.id.product_details_price);
		product_details_location = (TextView) rootView.findViewById(R.id.product_details_location);
		product_details_type = (TextView) rootView.findViewById(R.id.product_details_type);
		product_details_product_name = (TextView) rootView.findViewById(R.id.product_details_product_name);
		product_details_description = (TextView) rootView.findViewById(R.id.product_details_description);
		product_details_call =  (LayoutRipple) rootView.findViewById(R.id.product_details_call);
		product_details_email =  (LayoutRipple) rootView.findViewById(R.id.product_details_email);
		product_details_sms =  (LayoutRipple) rootView.findViewById(R.id.product_details_sms);
		
		HashMap<String,String> urlParams = new HashMap<String, String>();
		
		Client.volleyRawPost(URLUtils.URL_SINGLE_PRODUCT, urlParams, Product.class, null, productResponseHandler);
		

		return rootView;
	}
	
	private void populateUI(){
		if(product!= null){
			product_details_time.setText(product.getCreated_at());
			product_details_price.setText(product.price.toString());
			String productLocation = "";
			if(product.getSub_location()!=null){
				productLocation += product.getSub_location().getName()+", ";
			}
			if(product.getLocation() != null){
				productLocation += product.getLocation().getName();
			}
			product_details_location.setText(productLocation);
			
			product_details_product_name.setText(product.getTitle());
			product_details_description.setText(product.getDescription());
			
			
		}
	}
	
	VolleyResponseHandler<Product> productResponseHandler = new VolleyResponseHandler<Product>() {

		@Override
		public void onSuccess(Product response) {
			if(response != null){
				product = response;				
				populateUI();
			}
		}

		@Override
		public void onError(VolleyError error) {
			// TODO Auto-generated method stub
			
		}
	};

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.product_details_call:
			
			break;
		case R.id.product_details_email:
			SMSShare emailHandler = new SMSShare(getActivity());
			emailHandler.sendEmail("", "", "");
			break;
		case R.id.product_details_sms:
			SMSShare messaging = new SMSShare(getActivity());
			messaging.sendMessage("");
		break;

		default:
			break;
		}
		
	}
	
}
