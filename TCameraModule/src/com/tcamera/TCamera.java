package com.tcamera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraCharacteristics.Key;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;



@SuppressLint("NewApi")
public class TCamera {
	private Context context;
    private CameraManager cameraManager;
    private String[] cameraIds;
    private TCameraStateCallback cameraStateCallback;
    
    public TCamera(Context context){
    	this.context = context;
    	cameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
    	try {
			cameraIds = cameraManager.getCameraIdList();
			cameraStateCallback = new TCameraStateCallback();
		} catch (CameraAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean openFrontCamera(){
    	boolean success = false;
    	for(int i = 0; i<cameraIds.length; i++){
    		try {
				CameraCharacteristics charactirics = cameraManager.getCameraCharacteristics(cameraIds[i]);
				if(charactirics.equals(CameraCharacteristics.LENS_FACING_FRONT)){
					cameraManager.openCamera(cameraIds[i], cameraStateCallback, null);
					if(cameraStateCallback.isOpened){
						success = true;
						break;
					}
				}
				else success = false;
			} catch (CameraAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return success;
    }
    
    public CameraManager getCameraManager(){
    	return this.cameraManager;
    }
}
