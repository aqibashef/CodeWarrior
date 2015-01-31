package com.technext.cwc.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.technext.cwc.fragments.ProductDetailsFragment;

public class ProductDetailPagerAdapter extends FragmentPagerAdapter{
	
	int count;
	Context context;
	
	public ProductDetailPagerAdapter(FragmentManager fm, int count, Context context){
		super(fm);
		this.count = count;
		this.context = context;
	}
	
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}



	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return ProductDetailsFragment.newInstance(position);
	}

}
