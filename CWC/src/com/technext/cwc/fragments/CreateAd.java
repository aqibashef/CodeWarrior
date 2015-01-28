package com.technext.cwc.fragments;

import com.technext.cwc.MainActivity;
import com.technext.cwc.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CreateAd extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.create_add, container,
				false);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				MainActivity.screenWidth, (MainActivity.screenHeight * 2) / 6);
		ImageView imageViewBg = (ImageView) rootView.findViewById(R.id.create_ad_imageViewPBg);
		imageViewBg.setLayoutParams(params);
		
		return rootView;
	}
}
