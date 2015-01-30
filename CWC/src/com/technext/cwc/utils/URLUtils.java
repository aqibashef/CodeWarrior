package com.technext.cwc.utils;

public class URLUtils {
	
	/*mocky.io*/
	/*login--http://www.mocky.io/v2/54c7b5291f6a712d121514cc*/
	

	// login params
	
		private static final String URL_BASE = "http://www.mocky.io/v2/";
		// authentication
		public static final String URL_LOGIN = "54c7b5291f6a712d121514cc";
		public static final String URL_REGISTER = "54cbb56c96d6b2c611431f8b";

		// authenticated
		public static final String URL_PROFILE_EDIT = "54c7b5291f6a712d121514cc";
		public static final String URL_UPLOAD_PRO_PIC = "54c7b5291f6a712d121514cc";
		
		//create product--> http://www.mocky.io/v2/54c7d5f61f6a710d151514f3
		public static final String URL_CREATE_PRODUCT = "54c7d5f61f6a710d151514f3";
		public static final String URL_UPDATE_PRODUCT = "54c7d5f61f6a710d151514f3";
		
		
		// product lists--> http://www.mocky.io/v2/54c7dde01f6a71b4151514f6
		public static final String URL_PRODUCTS = "54c7dde01f6a71b4151514f6";
		public static final String URL__SEARCH_PRODUCTS = "54c7dde01f6a71b4151514f6";
		
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
		/**
		 * Param keys end
		 */

}
