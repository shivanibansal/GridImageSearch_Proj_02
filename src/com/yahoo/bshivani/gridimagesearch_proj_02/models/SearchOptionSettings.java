package com.yahoo.bshivani.gridimagesearch_proj_02.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SearchOptionSettings implements Serializable {

	private static final long serialVersionUID = 1L;
	public String argImageSize;
	public String argImageColor;
	public String argImageType;
	public String argSiteSearch;
	public String argFileType;

	public String toJsonString() {
		String strJsonString = "{   argImageSize : " + argImageSize + ","
								+ " argImageColor : " + argImageColor + ","
								+ " argImageType : " + argImageType;
		if (argSiteSearch.equals("") == false) {
			strJsonString += "," + " argSiteSearch : " + argSiteSearch;
		}
		strJsonString += "," + " argFileType : " + argFileType + "}";
		return strJsonString;
	}

	public void fromJsonString(String jsonSettingsStr) {
		JSONObject json = null;
		try {
			json = (JSONObject) new JSONParser().parse(jsonSettingsStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			argImageSize = json.getString("argImageSize");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			argImageColor = json.getString("argImageColor");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			argImageType = json.getString("argImageType");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			argSiteSearch = json.getString("argSiteSearch");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			argFileType = json.getString("argFileType");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
