package com.technext.cwc.utils;

public class URLUtils {
	
	/*mocky.io*/
	/*login--http://www.mocky.io/v2/54c7b5291f6a712d121514cc*/
	

	// login params
	
		private static final String URL_BASE = "http://www.mocky.io/v2/";
		// authentication
		//http://www.mocky.io/v2/54cbd72096d6b27d14431fa4
		public static final String URL_LOGIN = "54cc327f96d6b22a1c431ff4";
		public static final String URL_REGISTER = "54cc327f96d6b22a1c431ff4";

		// authenticated
		public static final String URL_PROFILE_EDIT = "54cc327f96d6b22a1c431ff4";
		public static final String URL_UPLOAD_PRO_PIC = "54cc327f96d6b22a1c431ff4";
		
		//create product--> http://www.mocky.io/v2/54c7d5f61f6a710d151514f3
		public static final String URL_CREATE_PRODUCT = "54c7d5f61f6a710d151514f3";
		public static final String URL_UPDATE_PRODUCT = "54c7d5f61f6a710d151514f3";
		
		public static final String URL_LOCATIONS = "54cbfbb996d6b22517431fc5";
		
		//http://www.mocky.io/v2/54cbe20e96d6b24e15431fb0
			public static final String URL_CATEGORIES = "54cbe20e96d6b24e15431fb0";
		
		//http://www.mocky.io/v2/54cbe1e096d6b24b15431faf
		
		// product lists--> http://www.mocky.io/v2/54c7dde01f6a71b4151514f6
		public static final String URL_PRODUCTS = "54cbe1e096d6b24b15431faf";
		public static final String URL__SEARCH_PRODUCTS = "54cc7aa122f5cf490407e0f8";
		
		//http://www.mocky.io/v2/54cc887b22f5cf450507e10a
		public static final String URL_SINGLE_PRODUCT = "54cc887b22f5cf450507e10a";
		
		public static String getAbsoluteUrl(String relativeUrl) {
			return URL_BASE + relativeUrl;
		}

		/**
		 * Param keys
		 */
		public static final String PARAM_USER_ID = "user_id";
		public static final String PARAM_SESSION_TOKEN = "session_token";
		public static final String PARAM_EMAIL = "email";
		public static final String PARAM_PASSWORD = "password";
		public static final String PARAM_NEW_PASSWORD = "new_password";
		public static final String PARAM_PASSWORD_CONFIRMATION = "password_confirmation";
		public static final String PARAM_PROFILE_PIC = "profile_pic";
		public static final String PARAM_FIRSTNAME = "firstname";
		public static final String PARAM_LASTNAME = "lastname";
		public static final String PARAM_MOBILE_NO = "mobile_no";
		
		public static final String PARAM_LOCATION = "location";
		public static final String PARAM_CATEORY = "category";
		public static final String PARAM_CRITERIA = "criteria";
		public static final String PARAM_MIN_PRICE = "min_price";
		public static final String PARAM_MAX_PRICE = "max_price";
		public static final String PARAM_SEARCH = "search";
		
		
		
		
		/**
		 * Param keys end
		 */

}
