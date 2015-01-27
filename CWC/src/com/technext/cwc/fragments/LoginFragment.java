package com.technext.cwc.fragments;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.protocol.ResponseConnControl;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.plus.PlusShare;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.technext.cwc.MainActivity;
import com.technext.cwc.R;
import com.technext.cwc.app.AppController;
import com.technext.cwc.database.datasource.ProductDataSource;
import com.technext.cwc.dialog.SplashProgressDialog;
import com.technext.cwc.fragments.RegistrationFragment.RegistrationCompleteListener;
import com.technext.cwc.http.Client;
import com.technext.cwc.http.GsonRequest;
import com.technext.cwc.listener.VolleyResponseHandler;
import com.technext.cwc.model.Product;
import com.technext.cwc.model.User;
import com.technext.cwc.utils.CommonUtils;

public class LoginFragment extends Fragment implements OnClickListener {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private final static String ARG_SECTION_NUMBER = "section_number";

	private EditText editTextEmail;
	private EditText editTextPassword;
	private Button buttonLogin;
	private LinearLayout registerLayout;

	private LoginSuccessListener loginSuccessListener;
	private RegistrationClickListener registerClickListener;
	private SplashProgressDialog progress;

	public LoginFragment() {

	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static LoginFragment newInstance(int sectionNumber) {
		LoginFragment fragment = new LoginFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ProductDataSource productSource = new ProductDataSource(getActivity());
		productSource.open();
		/*Product product = productSource.createProduct("My new Product", "Test product description", "test category", "Test type", "Test Location");
		Log.e("Product create", product.getTitle());*/
		List<Product> products = productSource.getAllProducts();
		Log.e("products start","start");
		Iterator<Product> iter = products.iterator();
		while(iter.hasNext()){
			Product product = iter.next();
			Log.e("product title--> ", "--> "+product.getTitle());
			Log.e("product id--> ", "--> "+product.getId());
		}
		Log.e("products end","end");
		productSource.close();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);

		editTextEmail = (EditText) rootView.findViewById(R.id.editTextEmail);
		editTextPassword = (EditText) rootView
				.findViewById(R.id.editTextPassword);
		registerLayout = (LinearLayout) rootView
				.findViewById(R.id.registerText);
		registerLayout.setOnClickListener(this);

		buttonLogin = (Button) rootView.findViewById(R.id.buttonLogin);
		buttonLogin.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));

		if (!(activity instanceof LoginSuccessListener)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks login success listener.");
		}

		loginSuccessListener = (LoginSuccessListener) activity;

		if (!(activity instanceof RegistrationClickListener)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks RegistrationClickListener.");
		}
		registerClickListener = (RegistrationClickListener) activity;

	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonLogin) {
			String email = editTextEmail.getText().toString();
			String password = editTextPassword.getText().toString();
			if (email == null || email.equalsIgnoreCase("")
					|| !CommonUtils.isValidEmail(email)) {
				Toast.makeText(getActivity(),
						"Please Provide a valid Email Address",
						Toast.LENGTH_SHORT).show();
			} else if (password == null || password.equalsIgnoreCase("")) {
				Toast.makeText(getActivity(), "Password Required",
						Toast.LENGTH_SHORT).show();
			} else {
				/*
				 * RequestParams params = new RequestParams();
				 * params.put(Client.PARAM_EMAIL, email);
				 * params.put(Client.PARAM_PASSWORD, password);
				 * Client.raw_post(Client.URL_LOGIN, params,
				 * loginResponseHandler);
				 */
				Map<String, String> params = new HashMap<String, String>();
				params.put(Client.PARAM_EMAIL, email);
				params.put(Client.PARAM_PASSWORD, password);
				// Client.raw_post(Client.URL_LOGIN, params,
				// loginResponseHandler);
				progress = new SplashProgressDialog(getActivity());
				progress.show();
				Client.volleyRawPost(Client.URL_LOGIN, params, User.class,
						null, new VolleyResponseHandler<User>() {

							@Override
							public void onSuccess(User user) {
								progress.dismiss();
								Toast.makeText(getActivity(), "Login success",
										Toast.LENGTH_SHORT).show();

								Client.setUser(user);
								Client.saveSession(getActivity(), user);
								Log.e("User email", user.getEmail());
								Toast.makeText(getActivity(),
										"User email--> " + user.getEmail(),
										Toast.LENGTH_LONG).show();
								loginSuccessListener.onloginComplete(user);

							}

							@Override
							public void onError(VolleyError error) {
								progress.dismiss();
								Toast.makeText(getActivity(),
										"error--> "+error.getMessage() ,
										Toast.LENGTH_LONG).show();

							}
						});
			}
		} else if (v.getId() == R.id.registerText) {
			registerClickListener.onRegisterClicked();
		}

		// GsonRequest<User> gsonReq = new GsonRequest<>(Client.URL_LOGIN,
		// User.class, headers, listener, errorListener);

	}

	AsyncHttpResponseHandler loginResponseHandler = new AsyncHttpResponseHandler() {

		public void onStart() {
			progress = new SplashProgressDialog(getActivity());
			progress.show();
		};

		public void onSuccess(int statusCode, String response) {
			Toast.makeText(getActivity(), "Login success", Toast.LENGTH_SHORT)
					.show();
			Gson gson = new Gson();
			User user = gson.fromJson(response, User.class);
			Client.setUser(user);
			Client.saveSession(getActivity(), user);
			Log.e("User email", user.getEmail());
			Toast.makeText(getActivity(), "User email--> " + user.getEmail(),
					Toast.LENGTH_LONG).show();
			
			
			loginSuccessListener.onloginComplete(user);
		};

		public void onFailure(Throwable arg0, String arg1) {
			Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT)
					.show();
		};

		public void onFinish() {
			progress.dismiss();
		};

	};

	public interface LoginSuccessListener {
		public void onloginComplete(User user);
	}

	public interface RegistrationClickListener {
		public void onRegisterClicked();
	}

}
