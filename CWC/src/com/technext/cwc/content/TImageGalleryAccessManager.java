package com.technext.cwc.content;

import java.util.ArrayList;
import com.technext.cwc.utils.TUtils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.provider.MediaStore;

/***
 * 
 * This Class is to access image file through Image Gallery apps available in the system.</br>
 * <strong>Minimum API Level 18 is required.</strong> </br></br>
 * Permission required:
 * <ul> 
 * <li><strong>"android.permission.MEDIA_CONTENT_CONTROL"</strong></li> 
 * <li><strong>"android.permission.READ_EXTERNAL_STORAGE"</strong></li>
 * </ul>
 * Procedure:
 * <ul>
 * <li>Initiate variable in <strong>onCreate(...)</strong> method of your activity with <strong>YourActivityClass.this</strong> parameter.</li>
 * <li>call <strong>TImageGalleryAccessManager instance.openGallery()</strong> method. </li>
 * <li>then you have to call <strong>TImageGalleryAccessManager instance.getImagePaths(Intent intent)</strong> method and you will get an <strong>"ArrayList< String >"</strong> containing the paths of images selected. <strong>N.B: You will always get an <strong>"ArrayList< String >"</strong> whether user selected one image or multiple.</strong></li>
 * </ul>
 * <strong>N.B. :</strong>the value of <strong>IMAGE_GALLERY_REQUEST_CODE</strong> is <strong>12</strong> and <strong>IMAGE_GALLERY_REQUEST_CODE_FOR_MULTIPLE_IMAGE_SELECTION</strong> is <strong>13</strong>. Do not use these for another request code while using <strong>TImageGalleryAccessManager</strong></br>
 *
 */

public class TImageGalleryAccessManager {
	
	private Activity context;
	
	public static final int IMAGE_GALLERY_REQUEST_CODE = 12; 
	public static final int IMAGE_GALLERY_REQUEST_CODE_FOR_MULTIPLE_IMAGE_SELECTION = 13;
	
	public TImageGalleryAccessManager(Activity context){
		this.context = context;
	}
	
	public void openGallery(int i){
		Intent imageGalleryIntent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		context.startActivityForResult(imageGalleryIntent, IMAGE_GALLERY_REQUEST_CODE);
		
	}
	
	public void openGallery(){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		context.startActivityForResult(Intent.createChooser(intent,"Select Picture"), IMAGE_GALLERY_REQUEST_CODE_FOR_MULTIPLE_IMAGE_SELECTION);
	}
	
	@SuppressLint("NewApi")
	public ArrayList<String> getImagePaths(Intent intent){
		ArrayList<String> uris = new ArrayList<String>();
		if (intent != null) {
			Uri uriForSingleImage = intent.getData();
			if(uriForSingleImage != null){
				uris.add(TUtils.getRealPathFromURI(context, uriForSingleImage));
			}
			else {
				 ClipData clipData = intent.getClipData();
		            if (clipData != null) {
		                for (int i = 0; i < clipData.getItemCount(); i++) {
		                    ClipData.Item item = clipData.getItemAt(i);
		                    Uri uri = item.getUri();

		                    //In case you need image's absolute path
		                    String path= TUtils.getRealPathFromURI(context, uri);
		                    uris.add(path);
		                }
		            }
			}           
        }
		return uris;
	}
}
