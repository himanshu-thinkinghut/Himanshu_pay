package com.elotouch.barcodescanner;

public class BarcodeReader {

	private native void setJNIBarCodeReader();
    private native int isBcrTurnedOn();

    public void turnOnLaser()
	{
		setJNIBarCodeReader();
	}
    
    public boolean isBcrOn()
   	{
    	if(isBcrTurnedOn()==1)
    		return true;
    	else
    		return false;
    	
   	}

}
