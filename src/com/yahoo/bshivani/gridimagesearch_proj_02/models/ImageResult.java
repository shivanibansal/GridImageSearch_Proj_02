package com.yahoo.bshivani.gridimagesearch_proj_02.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable {
	private static final long serialVersionUID = -742692364582093968L;
	public String 	fullUrl;
	public String	title;
	public String 	tbUrl;
	
	public ImageResult(JSONObject json) {
		try {
			this.fullUrl = json.getString("url");
			this.tbUrl = json.getString("tbUrl");
			this.title = json.getString("title");//titleNoFormatting"
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<ImageResult> fromJsonArray (JSONArray array) {
		ArrayList <ImageResult> arrImages = new ArrayList<ImageResult>();
			
		for (int i=0; i<array.length(); i++)
		{
			try {
				arrImages.add(new ImageResult(array.getJSONObject(i)));
			} catch (JSONException e){
				e.printStackTrace();
			}
		}
		return arrImages;
	}
}
