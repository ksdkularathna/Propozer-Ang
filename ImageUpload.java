package com.bargadss.Propozal.Admin.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageUpload {

	private long imageId;
	private String imagePath;
	private String email, imageType;
	private Date uploadDate;

	public ImageUpload() {
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		JSONObject jo = new JSONObject();

		jo.put("imageId", imageId);
		jo.put("imagePath", imagePath);
		jo.put("email", email);
		jo.put("imageType", imageType);

		if (uploadDate != null) {
			jo.put("uploadDate", formatter.format(uploadDate));
		}
		return jo;
	}

	// ***********************************************************************//
	public ImageUpload(org.json.simple.JSONObject inputJsonObj)
			throws org.json.JSONException, ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//
		// private long imageId;
		// private String imagePath;
		// private String email, imageType;
		// private Date uploadDate;

		if (inputJsonObj.containsKey("imageId"))
			imageId = (long) inputJsonObj.get("imageId");
		else
			imageId = 0;

		if (inputJsonObj.containsKey("imagePath"))
			imagePath = (String) inputJsonObj.get("imagePath");
		else
			imagePath = null;

		if (inputJsonObj.containsKey("email"))
			email = (String) inputJsonObj.get("email");
		else
			email = null;

		if (inputJsonObj.containsKey("imageType"))
			imageType = (String) inputJsonObj.get("imageType");
		else
			imageType = null;

		try {
			Date date = new Date();

			String d = formatter.format(date);

			uploadDate = formatter.parse(d);
		} catch (Exception e) {
			uploadDate = null;
		}

	}

	@Override
	public String toString() {
		return "ImageUpload [imageId=" + imageId + ", imagePath=" + imagePath
				+ ", email=" + email + ", imageType=" + imageType
				+ ", uploadDate=" + uploadDate + "]";
	}
}
