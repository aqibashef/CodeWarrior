package com.technext.cwc.fragments;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.LayoutRipple;
import com.squareup.picasso.Picasso;
import com.technext.cwc.CategoryActivity;
import com.technext.cwc.DescriptionDetailsActivity;
import com.technext.cwc.MainActivity;
import com.technext.cwc.R;
import com.technext.cwc.SubCategoryActivity;
import com.technext.cwc.content.TImageGalleryAccessManager;
import com.technext.cwc.sensor.TCameraAccessManager;

public class CreateAdFragment extends Fragment implements OnClickListener {
	private LayoutRipple layoutRipple_choose_category, layoutRipple_description, layoutRipple_your_info, submit;
	private TextView textView_choose_category, textView_description, textView_your_info;
	
	private String category = null, sub_category = null;
	
	private final static String ARG_SECTION_NUMBER = "section_number";
	private AlertDialog.Builder alertDialog;
	
	private TCameraAccessManager cameraAccessManager;
	private TImageGalleryAccessManager imageGalleryAccessManager;
	ImageView imageViewBg;
	
	public CreateAdFragment() {

	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static CreateAdFragment newInstance(int sectionNumber) {
		CreateAdFragment fragment = new CreateAdFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.create_add, container,
				false);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				MainActivity.screenWidth, MainActivity.screenHeight / 3);
		imageViewBg = (ImageView) rootView.findViewById(R.id.create_ad_imageViewPBg);
		imageViewBg.setLayoutParams(params);
		
		layoutRipple_choose_category = (LayoutRipple) rootView.findViewById(R.id.chooseCatagory);
		layoutRipple_description = (LayoutRipple) rootView.findViewById(R.id.writeDescription);
		layoutRipple_your_info = (LayoutRipple) rootView.findViewById(R.id.yourInfo);
		submit = (LayoutRipple) rootView.findViewById(R.id.create_ad_submit);
		
		textView_choose_category = (TextView) rootView.findViewById(R.id.chooseCatagory_textview);
		textView_description = (TextView) rootView.findViewById(R.id.writeDescription_textview);
		textView_your_info = (TextView) rootView.findViewById(R.id.yourInfo_textview);
		
		layoutRipple_choose_category.setOnClickListener(this);
		layoutRipple_description.setOnClickListener(this);
		layoutRipple_your_info.setOnClickListener(this);
		
		cameraAccessManager = new TCameraAccessManager(getActivity());
		imageGalleryAccessManager = new TImageGalleryAccessManager(getActivity());
		
		ImageView photo = (ImageView) rootView.findViewById(R.id.image_upload_create_ad);
		
		photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
				
		        // Setting Dialog Title
		        alertDialog.setTitle("Photo");
		        alertDialog.setCancelable(true);

		        // Setting Positive "Yes" Button
		        alertDialog.setPositiveButton("Take Photo",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog,int which) {
		                    	cameraAccessManager.openCameraApp();
		                    }
		                });
		        // Setting Negative "NO" Button
		        alertDialog.setNegativeButton("From Gallery",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int which) {
		                        imageGalleryAccessManager.openGallery(98);
		                    }
		                });
				alertDialog.show();
			}
		});
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		return rootView;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK){
//			Toast.makeText(getActivity(), ""+requestCode, Toast.LENGTH_SHORT).show();
			if(requestCode == 1234){
				category = data.getStringExtra(CategoryActivity.CATEGORY_KEY);
				sub_category = data.getStringExtra(SubCategoryActivity.SUB_CATEGORY_KEY);
				textView_choose_category.setText(category+", "+sub_category);
			}
			if(requestCode == 321){
				String title = data.getStringExtra(DescriptionDetailsActivity.KEY_TITLE);
				String desc = data.getStringExtra(DescriptionDetailsActivity.KEY_DESC);
				double price = data.getDoubleExtra(DescriptionDetailsActivity.KEY_PRICE, 0);
				textView_description.setText("Title: "+title+"\nPrice: "+price+"Descritption: "+desc);
			}
			if(TCameraAccessManager.IMAGE_CAPTURE_REQUEST_CODE == requestCode){
				File file = new File(cameraAccessManager.getAbsoluteImagePath());
				Picasso.with(getActivity()).load(file).into(imageViewBg);
			}
			
			if(TImageGalleryAccessManager.IMAGE_GALLERY_REQUEST_CODE == requestCode){
				Toast.makeText(getActivity(), "timage", Toast.LENGTH_LONG).show();
				if(data.getData() != null){
					Picasso.with(getActivity()).load(data.getData()).into(imageViewBg);
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId()){
		case R.id.chooseCatagory:
			intent = new Intent(getActivity(), CategoryActivity.class);
			getActivity().startActivityForResult(intent, 1234);
			break;
		case R.id.writeDescription:
			intent = new Intent(getActivity(), DescriptionDetailsActivity.class);
			intent.putExtra(CategoryActivity.CATEGORY_KEY, category);
			getActivity().startActivityForResult(intent,321);
			break;
		case R.id.yourInfo:
			// Creating alert Dialog with one Button
	        alertDialog = new AlertDialog.Builder(getActivity());

	        LayoutInflater li = LayoutInflater.from(getActivity());
	        LinearLayout price_min_max_layout = (LinearLayout) li.inflate(R.layout.user_info, null);
	        final EditText name = (EditText) price_min_max_layout.findViewById(R.id.your_info_name);
	        final EditText mob = (EditText) price_min_max_layout.findViewById(R.id.your_info_mobile);
	        final EditText email = (EditText) price_min_max_layout.findViewById(R.id.your_info_email);
			
	        // Setting Dialog Title
	        alertDialog.setTitle("Price");
	        alertDialog.setView(price_min_max_layout);
	        alertDialog.setCancelable(true);

	        // Setting Positive "Yes" Button
	        alertDialog.setPositiveButton("OK",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,int which) {
	                    	if(name.getText() != null && mob.getText() != null && email.getText() != null){
	                    		textView_your_info.setText("Name: "+name.getText().toString()+
	                    				"\nMobile: "+mob.getText().toString()+"\nEmail: "+email.getText().toString());
	                    	}
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
		default:
			break;
		}
	}
}
