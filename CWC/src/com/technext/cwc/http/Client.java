package com.technext.cwc.http;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.technext.cwc.app.AppController;
import com.technext.cwc.database.model.User;
import com.technext.cwc.listener.VolleyResponseHandler;


import com.technext.cwc.utils.URLUtils;

public class Client {
	// login params
	public static final String URL_BASE = "http://hellojewel.net/projects/cwc/public/";
	// authentication
	/*
	public static final String URL_LOGIN = "login";
	public static final String URL_REGISTER = "register";

	// authenticated
	public static final String URL_PROFILE_EDIT = "/user/edit";
	public static final String URL_UPLOAD_PRO_PIC = "/user/upload/profilePic";
*/
	/**
	 * Param keys
	 */
	/*public static final String PARAM_USER_ID = "user_id";
	public static final String PARAM_SESSION_TOKEN = "session_token";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_NEW_PASSWORD = "new_password";
	public static final String PARAM_PASSWORD_CONFIRMATION = "password_confirmation";
	public static final String PARAM_PROFILE_PIC = "profile_pic";
	public static final String PARAM_FIRSTNAME = "firstname";
	public static final String PARAM_LASTNAME = "lastname";*/
	/**
	 * Param keys end
	 */

	private static AsyncHttpClient client = new AsyncHttpClient();

	// static User object to easily get user infomation
	private static User user;
	private static Gson gson = new Gson();

	public static User getUser() {
		return user;
	}

	public static void setUser(User loggedUser) {
		user = loggedUser;
	}

	public static AsyncHttpClient getClient() {
		return client;
	}

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		// client.addHeader("User-Agent",
		// "AppleCoreMedia/1.0.0.9B206 (iPad; U; CPU OS 5_1_1 like Mac OS X; en_us)");
		client.get(URLUtils.getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * Post action without checking the session_token's expire_time
	 * 
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void raw_post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(URLUtils.getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * Post action which will check and refresh session_token when needed
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void post(Context context, String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		// check session_token expire_time, if expired, referesh it
		if (isValidSession(context))
			client.post(URLUtils.getAbsoluteUrl(url), params, responseHandler);
		else {
			Toast.makeText(context, "You are not Logged in", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/*private static String getAbsoluteUrl(String relativeUrl) {
		return URL_BASE + relativeUrl;
	}*/

	public static boolean isValidSession(Context context) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		long user_id = pref.getLong("user_id", 0);
		String session_token = pref.getString("session_token", null);
		if (user_id != 0 && session_token != null ) {
			return true;
		}

		return false; // no session_token provided or lost, need to get it again
	}

	/**
	 * Save the user data into SharedPreference
	 * 
	 * @param context
	 * @param user
	 */

	public static void saveSession(Context context, User user) {
		// save the token
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		/*
		 * editor.putString("session_token", user.getSession_token());
		 * editor.putLong("user_id", user.getId()); editor.putString("email",
		 * user.getEmail()); if(user.getProfile_pic_url_value()!=null){
		 * editor.putString("profile_pic_url", user.getProfile_pic_url_value());
		 * } if(user.getProfile_pic_extension()!=null){
		 * editor.putString("profile_pic_extension",
		 * user.getProfile_pic_extension()); }
		 */
		
		

		if(user.getServer_id() != null){
			editor.putLong("user_id", user.getServer_id());
			editor.putString("session_token", user.getSession_token());
		}
		if(user != null){
			if(new Select()
	        .from(User.class)
	        .executeSingle() == null){
				Log.e("user", "null");
				user.save();
			}
			
		}	

		editor.commit();
	}

	public static void removeSession(Context context) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.remove("user_id");
		editor.remove("session_token");
		new Delete().from(User.class).execute();
		editor.commit();
	}

	public static User getUserFromSession(Context context) {
		if (user != null) {
			return user;
		} else {
			
			SharedPreferences pref = PreferenceManager
					.getDefaultSharedPreferences(context);
			//long user_id = pref.getLong("user_id", 0);
			String session_token= pref.getString("session_token", null);
			//if(session_token != null){
				user =  new Select()
		        .from(User.class)
		        .executeSingle();
				
			//}
			
			if(user != null){
				Log.e("user not null", "null");
				return user;
			}
			return null;
			
			
		}
	}

// volley start 
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @param clazz
	 * @param headers
	 * @param responseHandler
	 */

	public static <T> void volleyRawPost(String url,
			Map<String, String> params, Class<T> clazz,
			Map<String, String> headers,
			final VolleyResponseHandler<T> responseHandler) {
		makeReqest(url, params, clazz, headers, responseHandler);
	}

	private static <T> void makeReqest(String url, Map<String, String> params,
			Class<T> clazz, Map<String, String> headers,
			final VolleyResponseHandler<T> responseHandler) {
		
		GsonRequest<T> gsonReq = new GsonRequest<T>(URLUtils.getAbsoluteUrl(url),
				params, clazz, headers, Method.POST,
				new Response.Listener<T>() {
					@Override
					public void onResponse(T response) {
						responseHandler.onSuccess(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						responseHandler.onError(error);
					}
				});
		AppController.getInstance().addToRequestQueue(gsonReq);
	}

}
