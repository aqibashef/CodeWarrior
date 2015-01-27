package com.technext.cwc.listener;

import com.android.volley.VolleyError;

public interface VolleyResponseHandler<T> {

	public void onSuccess(T response);
	public void onError(VolleyError error);
}
