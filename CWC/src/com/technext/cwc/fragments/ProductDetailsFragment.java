package com.technext.cwc.fragments;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.ScrollView;
import com.technext.cwc.MainActivity;
import com.technext.cwc.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ProductDetailsFragment extends Fragment {
	private final static String ARG_SECTION_NUMBER = "section_number";
	
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
		

		return rootView;
	}
}
