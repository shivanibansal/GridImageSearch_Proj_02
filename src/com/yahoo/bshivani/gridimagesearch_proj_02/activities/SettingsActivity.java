package com.yahoo.bshivani.gridimagesearch_proj_02.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.yahoo.bshivani.gridimagesearch_proj_02.R;
import com.yahoo.bshivani.gridimagesearch_proj_02.models.SearchOptionSettings;

public class SettingsActivity extends Activity {
	public SearchOptionSettings searchSettings;
	public boolean onFreshLaunch;
	public Spinner 	spImageSizeSpinner;
	public Spinner 	spImageColorSpinner;
	public Spinner 	spImageTypeSpinner;
	public Spinner 	spFileTypeSpinner;
	public EditText etSiteSearch;
	public boolean	isSettingChange = false; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		isSettingChange = false;
		searchSettings = new SearchOptionSettings();
		// Spinner
		spImageSizeSpinner = (Spinner) findViewById(R.id.spImageSizeSpinner);
		spImageColorSpinner = (Spinner) findViewById(R.id.spImageColorSpinner);
		spImageTypeSpinner = (Spinner) findViewById(R.id.spImageTypeSpinner);
		spFileTypeSpinner = (Spinner) findViewById(R.id.spFileTypeSpinner);
		etSiteSearch = (EditText) findViewById(R.id.etSiteSearch);
		// Get parameter from intent
		onFreshLaunch = getIntent().getBooleanExtra("onFreshLaunch", false);
//		if (onFreshLaunch == true)
		{
			searchSettings = (SearchOptionSettings) getIntent().getSerializableExtra("imageSearchOptions");
			// Read and set values
			setValuesInView(searchSettings);
		}
	}

	public void setValuesInView(SearchOptionSettings setting) {
		// Image Type
		{
			String imageTypeStr = setting.argImageType;
			String imageTypeSpValue = spImageTypeSpinner.getSelectedItem().toString();
			if ((imageTypeSpValue.equalsIgnoreCase(imageTypeStr) == false) && (imageTypeStr != "")) {
				setSpinnerToValue(spImageTypeSpinner, imageTypeStr);
				isSettingChange = true;
			}
		}
		// Image Size
		{
			String imageSizeStr = setting.argImageSize;
			String imageSizeSpValue = spImageSizeSpinner.getSelectedItem().toString();
			if ((imageSizeSpValue.equalsIgnoreCase(imageSizeStr) == false) && (imageSizeStr != "")) {
				setSpinnerToValue(spImageSizeSpinner, imageSizeStr);
				isSettingChange = true;
			}
		}
		// Image Color
		{
			String imageColorStr = setting.argImageColor;
			String imageColorSpValue = spImageColorSpinner.getSelectedItem().toString();
			if ((imageColorSpValue.equalsIgnoreCase(imageColorStr) == false) && (imageColorStr != "")) {
				setSpinnerToValue(spImageColorSpinner, imageColorStr);
				isSettingChange = true;
			}
		}
		// File Type
		{
			String fileTypeStr = setting.argImageType;
			String fileTypeSpValue = spFileTypeSpinner.getSelectedItem().toString();
			if ((fileTypeSpValue.equalsIgnoreCase(fileTypeStr) == false) && (fileTypeStr != "")) {
				setSpinnerToValue(spFileTypeSpinner, fileTypeStr);
				isSettingChange = true;
			}
		}
		// Site Search
		{
			String siteToSearch = setting.argSiteSearch;
			String siteToSearchValue = etSiteSearch.getText().toString();
			if ((siteToSearchValue.equalsIgnoreCase(siteToSearch) == false) && (siteToSearch != "")) {
				etSiteSearch.setText(siteToSearch);
				isSettingChange = true;
			}
		}
	}

	public void setSpinnerToValue(Spinner spinner, String value) {
		int index = 0;
		SpinnerAdapter adapter = spinner.getAdapter();
		for (int i = 0; i < adapter.getCount(); i++) {
			if (adapter.getItem(i).equals(value)) {
				index = i;
				break; // terminate loop
			}
		}
		spinner.setSelection(index);
	}
	
	@Override
	public void onBackPressed() {
		System.out.println("onBackPressed");
		
		createUserSettingObject ();
		Intent retIntent = new Intent();
		retIntent.putExtra("returnedSetting", searchSettings);
		retIntent.putExtra("isSettingChanged", isSettingChange);
		setResult(RESULT_OK, retIntent); 
		finish();
	}
	
	
	public void createUserSettingObject () {
		searchSettings.argImageType = spImageTypeSpinner.getSelectedItem().toString();
		searchSettings.argImageSize = spImageSizeSpinner.getSelectedItem().toString();
		searchSettings.argImageColor = spImageColorSpinner.getSelectedItem().toString();
		searchSettings.argFileType = spFileTypeSpinner.getSelectedItem().toString();
		searchSettings.argSiteSearch = etSiteSearch.getText().toString();
	}
}
