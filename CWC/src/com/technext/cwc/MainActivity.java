package com.technext.cwc;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;

import java.io.File;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.technext.cwc.database.model.User;
import com.technext.cwc.fragments.CreateAdFragment;
import com.technext.cwc.fragments.LoginFragment;
import com.technext.cwc.fragments.LoginFragment.LoginSuccessListener;
import com.technext.cwc.fragments.LoginFragment.RegistrationClickListener;
import com.technext.cwc.fragments.ProductDetailsFragment;
import com.technext.cwc.fragments.ProductListFragment;
import com.technext.cwc.fragments.ProfileFragment;
import com.technext.cwc.fragments.RegistrationFragment;
import com.technext.cwc.fragments.RegistrationFragment.RegistrationCompleteListener;
import com.technext.cwc.fragments.ShareContentFragment;
import com.technext.cwc.fragments.SocialNetworkChooserFragment;
import com.technext.cwc.http.Client;

import com.utils.ImageFetcher.Callback;

public class MainActivity extends MaterialNavigationDrawer implements
//		NavigationDrawerFragment.NavigationDrawerCallbacks, 
		LoginSuccessListener, RegistrationCompleteListener, Callback,
	      ConnectionCallbacks, OnConnectionFailedListener, RegistrationClickListener{

//	 private static final String IMAGE_CACHE_DIR = "cwc_tassignment1";
//	 public static ImageFetcher imageLoader; //use to load image from internet
	 public MaterialSection<Fragment> sectionLogin, sectionProfile;
	 
	 
	 
	 //dfjkgbdjf
	 
	 
	 public static int screenWidth;
	 public static int screenHeight;

	 private static ProgressDialog pd;
     static Context context;
	 
	 public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";
	 public final static String ARG_SECTION_NUMBER = "section_number";

	@Override
	public void init(Bundle savedInstanceState) {
		
//		setDrawerHeaderImage(R.drawable.profile_bg);
//        setUsername("My App Name");
//        setUserEmail("My version build");
//        setFirstAccountPhoto(getResources().getDrawable(R.drawable.photo));

        // create sections
//        this.addSection(newSection("Login", LoginFragment.newInstance(1)).setSectionColor(Color.parseColor("#009688")));CreateAdFragment
        if(Client.getUserFromSession(getApplicationContext()) == null){
			//Toast.makeText(getApplicationContext(), "user logged out", Toast.LENGTH_SHORT).show();
        	afterLogout(true);
		}else{
			//Toast.makeText(getApplicationContext(), "user logged in"+Client.getUser().getProfile_pic_url(), Toast.LENGTH_SHORT).show();
			afterLogin(true);
		}
        
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
		
//		initImageLoader(screenHeight,screenWidth);
		context = this;

        // create bottom section
//        this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
	}
	
	
	
	 @Override
	 public void onPause() {
	     super.onPause();
//	     imageLoader.setPauseWork(false);
//	     imageLoader.setExitTasksEarly(true);
//	     imageLoader.flushCache();
	 }
	 @Override
	 public void onResume() {
	     super.onResume();
//	     imageLoader.setExitTasksEarly(false);
	 }
	 @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//mGoogleApiClient.connect();
	}
	 
	 @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		/* if (mGoogleApiClient.isConnected()) {
		      mGoogleApiClient.disconnect();
		 }*/
	}
	 
	 @Override
	 public void onDestroy() {
	     super.onDestroy();
//	     imageLoader.closeCache();
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if(id == R.id.action_logout){
			if(Client.getUserFromSession(getApplicationContext()) != null){
				logout();
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void logout() {
		Fragment fragment = (Fragment) this.getCurrentSection().getTargetFragment();
//		this.getf
		if(fragment instanceof SocialNetworkChooserFragment){
			SocialNetworkManager mSocialNetworkManager = ((SocialNetworkChooserFragment) fragment).getSocialNetworkManager();
			if(mSocialNetworkManager != null && !mSocialNetworkManager.getInitializedSocialNetworks().isEmpty()) {
                List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
                for (SocialNetwork socialNetwork : socialNetworks) {
                	if(socialNetwork.isConnected()){
	                	socialNetwork.cancelAll();
	                    socialNetwork.logout();
                	}
                }
            }
		}
		
		if(fragment instanceof ShareContentFragment){
			SocialNetwork socialNetwork = ((ShareContentFragment) fragment).getSocialNetwork();
			if(socialNetwork != null && socialNetwork.isConnected()){
            	socialNetwork.cancelAll();
                socialNetwork.logout();
        	}
		}
		
		Client.removeSession(this);
		Client.setUser(null);
		
		afterLogout(false);
	}

	@Override
	public void onloginComplete(User user) {
		//Toast.makeText(getApplicationContext(), "In Activity email--> "+user.getEmail(), Toast.LENGTH_SHORT).show();
//		mNavigationDrawerFragment.changeDataset(drawerItems_login);
//		redirect(1);
		afterLogin(false);
	}

	@Override
	public void onRegistrationComplete(User user) {
		afterLogin(false);
	}
	
	@Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
	      if (fragment != null) {
	          fragment.onActivityResult(requestCode, resultCode, data);
	      }else{
	    	  Object object = this.getCurrentSection().getTargetFragment();
	    	  if(object != null && object instanceof Fragment && object instanceof CreateAdFragment){
	    		  ((CreateAdFragment)object).onActivityResult(requestCode, resultCode, data);
	    	  }
	      }
	  }


	@Override
	public void getDrawable(Drawable drawable, Object name, File file) {
		//Toast.makeText(getApplicationContext(), ""+name, Toast.LENGTH_SHORT).show();
		//Log.e("name--> ", ""+name);
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		 /*if (!mIntentInProgress && result.hasResolution()) {
			    try {
			      mIntentInProgress = true;
			      startIntentSenderForResult(result.getResolution().getIntentSender(),
			          RC_SIGN_IN, null, 0, 0, 0);
			    } catch (SendIntentException e) {
			      // The intent was canceled before it was sent.  Return to the default
			      // state and attempt to connect to get an updated ConnectionResult.
			      mIntentInProgress = false;
			      mGoogleApiClient.connect();
			    }
			  }*/
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onConnectionSuspended(int cause) {
		// TODO Auto-generated method stub
		//mGoogleApiClient.connect();
		
	}
	
	
	public static void showProgress(String message) {
        pd = new ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(message);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

	public static void hideProgress() {
        pd.dismiss();
    }
	
//	private void redirect(int position){
//		FragmentManager fragmentManager = getSupportFragmentManager();
//		fragmentManager
//				.beginTransaction()
//				.replace(R.id.container,
//						chooseFragment(position)).commit();
//		onSectionAttached(position);
//	}

	@Override
	public void onRegisterClicked() {
		this.clearSectionsView();
		this.initLists();
		
		sectionLogin= newSection(getString(R.string.title_login), LoginFragment.newInstance(1)).setSectionColor(getResources().getColor(R.color.colorPrimary));
        sectionProfile = newSection(getString(R.string.title_profile), ProfileFragment.newInstance(0)).setSectionColor(getResources().getColor(R.color.colorPrimary));
        MaterialSection selectionRegistration = newSection(getString(R.string.title_registration),RegistrationFragment.newInstance(2));
        this.addSection(sectionLogin);
        this.addSection(selectionRegistration.setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection(getString(R.string.title_share),SocialNetworkChooserFragment.newInstance(3)).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection("Product Details",ProductDetailsFragment.newInstance(4)).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection("Create Ad", CreateAdFragment.newInstance(5)).setSectionColor(getResources().getColor(R.color.colorPrimary)));
       
        this.setCurrentSection(selectionRegistration);
        this.onClickCustom(selectionRegistration);
        selectionRegistration.select();
        this.setSectionsTouch(true);
	}
	
	public void afterLogin(boolean isOnCreate){
		if(!isOnCreate){
			this.clearSectionsView();
			this.initLists();
		}
		sectionLogin= newSection(getString(R.string.title_login), LoginFragment.newInstance(1)).setSectionColor(getResources().getColor(R.color.colorPrimary));
        sectionProfile = newSection(getString(R.string.title_profile), ProfileFragment.newInstance(0)).setSectionColor(getResources().getColor(R.color.colorPrimary));
		MaterialAccount account = new MaterialAccount(this.getResources(),"NeoKree","neokree@gmail.com", R.drawable.photo2, R.drawable.profile_bg);
        this.addAccount(account);
		this.addSection(sectionProfile);
        this.addSection(newSection(getString(R.string.title_share),SocialNetworkChooserFragment.newInstance(3)).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection("Product List",new ProductListFragment()).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection("Product Details",ProductDetailsFragment.newInstance(4)).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection("Create Ad",new CreateAdFragment()).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addBottomSection(newSection("Logout",R.drawable.ic_settings_black_24dp,new MaterialSectionListener() {
			
			@Override
			public void onClick(MaterialSection section) {
				Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_LONG).show();
			}
		}));
        if(!isOnCreate){
	        this.setCurrentAccount(account);
	        this.setCurrentSection(sectionProfile);
	        this.onClickCustom(sectionProfile);
	        sectionProfile.select();
	        this.setSectionsTouch(true);
        }
	}
	
	public void afterLogout(boolean isOnCreate){
		if(!isOnCreate){
			this.clearSectionsView();
			this.initLists();
		}
		sectionLogin= newSection(getString(R.string.title_login), LoginFragment.newInstance(1)).setSectionColor(getResources().getColor(R.color.colorPrimary));
        sectionProfile = newSection(getString(R.string.title_profile), ProfileFragment.newInstance(0)).setSectionColor(getResources().getColor(R.color.colorPrimary));
    	this.addSection(sectionLogin);
        this.addSection(newSection(getString(R.string.title_registration),RegistrationFragment.newInstance(2)).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection(getString(R.string.title_share),SocialNetworkChooserFragment.newInstance(3)).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection("Product Details",ProductDetailsFragment.newInstance(4)).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        this.addSection(newSection("Create Ad",new CreateAdFragment()).setSectionColor(getResources().getColor(R.color.colorPrimary)));
        if(!isOnCreate){
	        this.setCurrentSection(sectionLogin);
	        this.onClickCustom(sectionLogin);
	        sectionLogin.select();
	        this.setSectionsTouch(true);
        }
	}
}
