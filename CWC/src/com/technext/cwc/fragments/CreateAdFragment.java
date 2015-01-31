package com.technext.cwc.fragments;

import com.gc.materialdesign.views.LayoutRipple;
import com.technext.cwc.CategoryActivity;
import com.technext.cwc.MainActivity;
import com.technext.cwc.R;
import com.technext.cwc.SubCategoryActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAdFragment extends Fragment implements OnClickListener {
	private LayoutRipple layoutRipple_choose_category, layoutRipple_description, layoutRipple_your_info;
	private TextView textView_choose_category, textView_description, textView_your_info;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.create_add, container,
				false);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				MainActivity.screenWidth, MainActivity.screenHeight / 3);
		ImageView imageViewBg = (ImageView) rootView.findViewById(R.id.create_ad_imageViewPBg);
		imageViewBg.setLayoutParams(params);
		
		layoutRipple_choose_category = (LayoutRipple) rootView.findViewById(R.id.chooseCatagory);
		layoutRipple_description = (LayoutRipple) rootView.findViewById(R.id.writeDescription);
		layoutRipple_your_info = (LayoutRipple) rootView.findViewById(R.id.yourInfo);
		
		textView_choose_category = (TextView) rootView.findViewById(R.id.chooseCatagory_textview);
		textView_description = (TextView) rootView.findViewById(R.id.writeDescription_textview);
		textView_your_info = (TextView) rootView.findViewById(R.id.yourInfo_textview);
		
		layoutRipple_choose_category.setOnClickListener(this);
		layoutRipple_description.setOnClickListener(this);
		layoutRipple_your_info.setOnClickListener(this);
		
		return rootView;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK){
			Toast.makeText(getActivity(), ""+requestCode, Toast.LENGTH_SHORT).show();
			if(requestCode == 123){
				textView_choose_category.setText(data.getStringExtra(CategoryActivity.CATEGORY_KEY)+", "+data.getStringExtra(SubCategoryActivity.SUB_CATEGORY_KEY));
			}
			if(requestCode == 2){
				
			}
			if(requestCode == 3){
				
			}
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId()){
		case R.id.chooseCatagory:
			intent = new Intent(getActivity(), CategoryActivity.class);
			getActivity().startActivityForResult(intent, 123);
			break;
		case R.id.writeDescription:
			intent = new Intent(getActivity(), CategoryActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.yourInfo:
			intent = new Intent(getActivity(), CategoryActivity.class);
			getActivity().startActivity(intent);
			break;
		default:
			break;
		}
	}
}
