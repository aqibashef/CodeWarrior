package com.technext.cwc.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

public class TUtils {
	public static String getRealPathFromURI(Context context, Uri contentUri) {
	    Cursor cursor = null;
	    try {
	        String[] proj = { MediaStore.Images.Media.DATA };
	        cursor = context.getContentResolver().query(contentUri, proj, null,
	                null, null);
	        int column_index = cursor
	                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();
	        return cursor.getString(column_index);
	    } finally {
	        if (cursor != null) {
	            cursor.close();
	        }
	    }
	}
	
	public static void showToast(Context context, String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
