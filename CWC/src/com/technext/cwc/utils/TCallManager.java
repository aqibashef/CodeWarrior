package com.technext.cwc.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/***
 * 
 * This Activity requires "android.permission.CALL_PHONE" permission
 *
 */

public class TCallManager {
	
	private Context context;
	
	public TCallManager(Context context){
		this.context = context;
	}
	
	public void call(String id){		
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+id));
		
		if(callIntent.resolveActivity(context.getPackageManager())!=null){
			Toast.makeText(context, "calling", Toast.LENGTH_SHORT).show();
			context.startActivity(callIntent);
		}
	}
	
	public void openInDialer(String id){
		Intent callIntent = new Intent(Intent.ACTION_DIAL);
		callIntent.setData(Uri.parse("tel:"+id));
		
		if(callIntent.resolveActivity(context.getPackageManager())!=null){
			Toast.makeText(context, "calling", Toast.LENGTH_SHORT).show();
			context.startActivity(callIntent);
		}
	}
	
	public void openCallService(){
		Intent callIntent = new Intent(Intent.ACTION_CALL_BUTTON);
		//callIntent.setData(Uri.parse("tel:"+id));
		
		if(callIntent.resolveActivity(context.getPackageManager())!=null){
			Toast.makeText(context, "calling", Toast.LENGTH_SHORT).show();
			context.startActivity(callIntent);
		}
	}
}
