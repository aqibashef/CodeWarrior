package com.technext.cwc.http;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonRequest<T> extends Request<T> {
	private final Gson gson = new Gson();
	private final Class<T> clazz;
	private final Map<String, String> headers;
	private final Listener<T> listener;
	private final int method;
	private final Map<String, String> params;

	/**
	 * Make a request based on request method and return a parsed object from JSON.
	 * 
	 * @param url
	 *            URL of the request to make
	 * @param params
	 * 			Map of request params
	 * @param clazz
	 *            Relevant class object, for Gson's reflection
	 * @param headers
	 *            Map of request headers
	 * @param listner success listener
	 * @param error errorListener
	 */
	public GsonRequest(String url, Map<String, String> params, Class<T> clazz,
			Map<String, String> headers, int method, Listener<T> listener,
			ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.headers = headers;
		this.listener = listener;
		this.method = method;
		this.params = params;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers != null ? headers : super.getHeaders();
	}

	@Override
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return Response.success(gson.fromJson(json, clazz),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	public int getMethod() {
		// TODO Auto-generated method stub
		return this.method;
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return this.params;
	}

	@Override
	protected VolleyError parseNetworkError(VolleyError volleyError) {
		 if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
             VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
             volleyError = error;
         }
		 return volleyError;
	}
	

}
