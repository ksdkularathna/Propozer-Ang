package com.bargadss.Propozal.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageProcessing {

	private long imageId;
	private byte[] image;
	//private String imageString;

	public ImageProcessing() {
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
		
	

//	public String getImageString() {
//		return imageString;
//	}
//
//	public void setImageString(String imageString) {
//		this.imageString = imageString;
//	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		jo.put("imageId", imageId);
		jo.put("image", image);
		//jo.put("images", imageString);

		return jo;
	}

}
