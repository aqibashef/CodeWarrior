package com.technext.cwc;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;

import java.io.File;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.technext.cwc.fragments.LoginFragment;
import com.technext.cwc.fragments.LoginFragment.LoginSuccessListener;
import com.technext.cwc.fragments.LoginFragment.RegistrationClickListener;
import com.technext.cwc.fragments.RegistrationFragment;
import com.technext.cwc.fragments.RegistrationFragment.RegistrationCompleteListener;
import com.technext.cwc.fragments.SocialNetworkChooserFragment;
import com.technext.cwc.http.Client;
import com.technext.cwc.model.User;
import com.utils.ImageCache.ImageCacheParams;
import com.utils.ImageFetcher;
import com.utils.ImageFetcher.Callback;

public class MainActivity extends MaterialNavigationDrawer implements
//		NavigationDrawerFragment.NavigationDrawerCallbacks, 
		LoginSuccessListener, RegistrationCompleteListener, Callback,
	      ConnectionCallbacks, OnConnectionFailedListener, RegistrationClickListener{

	 private static final String IMAGE_CACHE_DIR = "cwc_tassignment1";
	 public static ImageFetcher imageLoader; //use to load image from internet

	 
	 
	 
	 //dfjkgbdjf
	 
	 
	 public static int screenWidth;
	 public static int screenHeight;

	 private static ProgressDialog pd;
     static Context context;
	 
	 public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";
	 public final static String ARG_SECTION_NUMBER = "section_number";


	 
	 /* Request code used to invoke sign in user interactions. */
	  private static final int RC_SIGN_IN = 0;

	  /* Client used to interact with Google APIs. */
	 // private GoogleApiClient mGoogleApiClient;

	  /* A flag indicating that a PendingIntent is in progress and prevents
	   * us from starting further intents.
	   */
	  private boolean mIntentInProgress;
	
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
//	private NavigationDrawerFragment mNavigationDrawerFragment;
//	
//	private ArrayList<String> drawerItems_login = new ArrayList<String>();
//	private ArrayList<String> drawerItems_logout = new ArrayList<String>();

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
//	private CharSequence mTitle;

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		drawerItems_logout.add(getString(R.string.title_login));
		drawerItems_logout.add(getString(R.string.title_registration));
		drawerItems_logout.add(getString(R.string.title_share));
		
		drawerItems_login.add(getString(R.string.title_profile));
		drawerItems_login.add(getString(R.string.title_logout));
		drawerItems_login.add(getString(R.string.title_share));
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
		
		
		final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
		
		initImageLoader(screenHeight,screenWidth);
		if(Client.getUserFromSession(getApplicationContext()) == null){
			//Toast.makeText(getApplicationContext(), "user logged out", Toast.LENGTH_SHORT).show();
			mNavigationDrawerFragment.changeDataset(drawerItems_logout);
		}else{
			//Toast.makeText(getApplicationContext(), "user logged in"+Client.getUser().getProfile_pic_url(), Toast.LENGTH_SHORT).show();
			mNavigationDrawerFragment.changeDataset(drawerItems_login);
		}
		
		  mGoogleApiClient = new GoogleApiClient.Builder(this)
	        .addConnectionCallbacks(MainActivity.this)
	        .addOnConnectionFailedListener(this)
	        .addApi(Plus.API)
	        .addScope(Plus.SCOPE_PLUS_LOGIN)
	        .build();
		
	}*/
	
	@Override
	public void init(Bundle savedInstanceState) {
		MaterialAccount account = new MaterialAccount(this.getResources(),"NeoKree","neokree@gmail.com", R.drawable.photo2, R.drawable.bamboo);
        this.addAccount(account);
		
//		setDrawerHeaderImage(R.drawable.profile_bg);
//        setUsername("My App Name");
//        setUserEmail("My version build");
//        setFirstAccountPhoto(getResources().getDrawable(R.drawable.photo));

        // create sections
        this.addSection(newSection("Login", LoginFragment.newInstance(1)).setSectionColor(Color.parseColor("#009688")));
        this.addSection(newSection("Registaration",RegistrationFragment.newInstance(2)).setSectionColor(Color.parseColor("#009688")));
        this.addSection(newSection("Share",R.drawable.camera,SocialNetworkChooserFragment.newInstance(3)).setSectionColor(Color.parseColor("#009688")));
        
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
		
		initImageLoader(screenHeight,screenWidth);
		context = this;
		
		// add account sections
        this.addAccountSection(newSection("Account settings",R.drawable.ic_settings_black_24dp,new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection section) {
                Toast.makeText(MainActivity.this,"Account settings clicked",Toast.LENGTH_SHORT).show();

                // for default section is selected when you click on it
//                section.unSelect(); // so deselect the section if you want
            }
        }));

        // create bottom section
//        this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
	}
	
	 @Override
	 public void onPause() {
	     super.onPause();
	     imageLoader.setPauseWork(false);
	     imageLoader.setExitTasksEarly(true);
	     imageLoader.flushCache();
	 }
	 @Override
	 public void onResume() {
	     super.onResume();
	     imageLoader.setExitTasksEarly(false);
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
	     imageLoader.closeCache();
	 }

	/*@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						chooseFragment(position + 1)).commit();
	}*/

	public void onSectionAttached(int number) {
		
//		if(Client.getUserFromSession(getApplicationContext()) == null){
//			switch (number) {
//				case 1:
//					mTitle = getString(R.string.title_login);
//					break;
//				case 2:
//					mTitle = getString(R.string.title_registration);
//					break;
//				case 3:
//					mTitle = getString(R.string.title_share);
//					break;
//				}
//		}else if(Client.getUserFromSession(getApplicationContext()) != null){
//			switch (number) {
//				case 1:
//					mTitle = getString(R.string.title_profile);
//					break;
//				case 2:
//					mTitle = getString(R.string.title_logout);
//					break;
//				case 3:
//					mTitle = getString(R.string.title_share);
//					break;
//				}
//		}
	}

//	public void restoreActionBar() {
//		ActionBar actionBar = getSupportActionBar();
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//		actionBar.setDisplayShowTitleEnabled(true);
//		actionBar.setTitle(mTitle);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		if (!mNavigationDrawerFragment.isDrawerOpen()) {
//			// Only show items in the action bar relevant to this screen
//			// if the drawer is not showing. Otherwise, let the drawer
//			// decide what to show in the action bar.
//			getMenuInflater().inflate(R.menu.main, menu);
//			restoreActionBar();
//			return true;
//		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if(id == R.id.action_logout){
			if(Client.getUserFromSession(getApplicationContext()) != null){
//				logout();
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
//	private Fragment fragment = null;
	
//	private Fragment chooseFragment(int position){
////		switch (position) {
////		case 1:
////			fragment = LoginFragment.newInstance(position);
////			break;
////
////		case 2:
////			fragment = RegistrationFragment.newInstance(position);
////			break;
////
////		case 3:
////			fragment = ProfileFragment.newInstance(position);
////			break;
////			
////		case 4:
////			fragment = SocialNetworkChooserFragment.newInstance(position);
////			break;
////			
////		default:
////			fragment = PlaceholderFragment.newInstance(position);
////			break;
////		}
//		
//		if(Client.getUserFromSession(getApplicationContext()) == null){
//			switch (position) {
//				case 1:
//					fragment = LoginFragment.newInstance(position);
//					break;
//				case 2:
//					fragment = RegistrationFragment.newInstance(position);
//					break;
//				case 3:
//					fragment = SocialNetworkChooserFragment.newInstance(position);
//					break;
//				}
//		}else if(Client.getUserFromSession(getApplicationContext()) != null){
//			switch (position) {
//				case 1:
//					fragment = ProfileFragment.newInstance(position);
//					break;
//				case 2:
//					logout();
//					break;
//				case 3:
//					fragment = SocialNetworkChooserFragment.newInstance(position);
//					break;
//				}
//		}
//		
//		return fragment;
//	}

//	private void logout() {
//		if(fragment instanceof SocialNetworkChooserFragment){
//			SocialNetworkManager mSocialNetworkManager = ((SocialNetworkChooserFragment) fragment).getSocialNetworkManager();
//			if(mSocialNetworkManager != null && !mSocialNetworkManager.getInitializedSocialNetworks().isEmpty()) {
//                List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
//                for (SocialNetwork socialNetwork : socialNetworks) {
//                	if(socialNetwork.isConnected()){
//	                	socialNetwork.cancelAll();
//	                    socialNetwork.logout();
//                	}
//                }
//            }
//		}
//		
//		if(fragment instanceof ShareContentFragment){
//			SocialNetwork socialNetwork = ((ShareContentFragment) fragment).getSocialNetwork();
//			if(socialNetwork != null && socialNetwork.isConnected()){
//            	socialNetwork.cancelAll();
//                socialNetwork.logout();
//        	}
//		}
//		
//		Client.removeSession(this);
//		Client.setUser(null);
//		
//		mNavigationDrawerFragment.changeDataset(drawerItems_logout);
//		redirect(1);
//	}

	@Override
	public void onloginComplete(User user) {
		//Toast.makeText(getApplicationContext(), "In Activity email--> "+user.getEmail(), Toast.LENGTH_SHORT).show();
//		mNavigationDrawerFragment.changeDataset(drawerItems_login);
//		redirect(1);
	}

	@Override
	public void onRegistrationComplete(User user) {
		//Toast.makeText(getApplicationContext(), "In Activity email--> "+user.getEmail(), Toast.LENGTH_SHORT).show();
//		mNavigationDrawerFragment.changeDataset(drawerItems_login);
//		redirect(1);
	}
	
	private void initImageLoader(int screenHeight, int screenWidth){
		int longest = (screenHeight > screenWidth ? screenHeight : screenHeight) / 2;
		 ImageCacheParams cacheParams = new ImageCacheParams(MainActivity.this, IMAGE_CACHE_DIR);
		 cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory
		 imageLoader = new ImageFetcher(MainActivity.this, longest);
		 imageLoader.setLoadingImage(R.drawable.empty_photo);
		 imageLoader.useLoadingImageForFadein(true);
		 imageLoader.addImageCache(MainActivity.this.getSupportFragmentManager(), cacheParams);
		 imageLoader.setCallback(MainActivity.this);
	}

	
	
	
	
	
	@Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
	      if (fragment != null) {
	          fragment.onActivityResult(requestCode, resultCode, data);
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
//		redirect(2);
		
	}
}
