package com.tcamera;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraDevice;

@SuppressLint("NewApi") 
public class TCameraStateCallback extends CameraDevice.StateCallback{
	private CameraDevice cameraDevice;
	public boolean isOpened;
	
	public TCameraStateCallback(){
		super();
		isOpened = false;
	}

	@Override
	public void onOpened(CameraDevice camera) {
		// TODO Auto-generated method stub
		this.cameraDevice = camera;
		isOpened = true;
	}

	@Override
	public void onDisconnected(CameraDevice camera) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(CameraDevice camera, int error) {
		// TODO Auto-generated method stub
		
	}
	
	public CameraDevice getCameraDevice() throws NullPointerException{
		if(isOpened){
			return cameraDevice;
		}
		else return null;
	}

}
