package com.yahoo.bshivani.gridimagesearch_proj_02.activities;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.bshivani.gridimagesearch_proj_02.R;
import com.yahoo.bshivani.gridimagesearch_proj_02.adapters.ImageResultsAdapter;
import com.yahoo.bshivani.gridimagesearch_proj_02.listener.EndlessScrollListener;
import com.yahoo.bshivani.gridimagesearch_proj_02.models.GoogleSearchAPI;
import com.yahoo.bshivani.gridimagesearch_proj_02.models.ImageResult;
import com.yahoo.bshivani.gridimagesearch_proj_02.models.SearchOptionSettings;


public class ImageSearchActivity extends Activity {
	public 	EditText 				etSearchQuery;
	public 	GridView					gvImageGrid;
	public 	SearchOptionSettings	searchSettings;
	public 	boolean 				onFreshLaunch;	
	public 	final int				REQUEST_CODE = 10;
	public	GoogleSearchAPI			searchAPIParamsObj;		
	public 	int 					mResPerPage = 8;
	public 	int						mResOffset = 0;
	public	String					prevQueryString = "";
	public 	ArrayList<ImageResult>	imageResults;
	public	ImageResultsAdapter		aImageResults;
	public	static boolean 			mFreshSearch = true;					

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        
        etSearchQuery = (EditText) findViewById(R.id.etSearchQuery);
        gvImageGrid=(GridView) findViewById(R.id.gvImageGrid);
        setupViews();
        searchSettings	= new SearchOptionSettings();
        onFreshLaunch = true;

    	// pass arguments to set default Settings - On Launch 
    	// TODO : Read from File and populate that settings. For time being, sending default settings
//        readItems();
    	searchSettings.argImageSize = GoogleSearchAPI.STR_ARR_VAL_IMAGE_SIZE_SMALL;
    	searchSettings.argImageColor = GoogleSearchAPI.STR_VAL_IMAGE_COLOR_GRAY;
    	searchSettings.argImageType = GoogleSearchAPI.STR_VAL_IMAGE_TYPE_CLIPART;
    	searchSettings.argSiteSearch = "";
    	searchSettings.argFileType = GoogleSearchAPI.STR_VAL_FILE_TYPE_JPG;
    	// Set to Google API params
    	searchAPIParamsObj = new GoogleSearchAPI();
    	searchAPIParamsObj.setFromSearchSettingsObj(searchSettings);
    	// Create the data source
    	imageResults = new ArrayList<ImageResult>();
    	// Attaches the data source to an adapter
    	aImageResults = new ImageResultsAdapter(this, imageResults);
    	// Link the adapter to adapterView (gridView)
    	gvImageGrid.setAdapter(aImageResults);
    }
    
    // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset) {
    	// This method probably sends out a network request and appends new data items to your adapter. 
    	// Use the offset value and add it as a parameter to your API request to retrieve paginated data.
    	// Deserialize API response and then construct new objects to append to the adapter
    	System.out.println("customLoadMoreDataFromApi");
    	makeNewNetworkReq();
    	
    }

    private void setupViews() {
    	gvImageGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Launch the new display activity
				// Creating an Intent
				Intent i = new Intent(ImageSearchActivity.this, ImageDisplayActivity.class);
				// get the image result to display
				ImageResult img = imageResults.get(position); 
				// pass the image result into the intent
				i.putExtra("imageItem", img);
				// Launch the new activity
				startActivity(i);
			}
		});
    	
    	gvImageGrid.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				System.out.println("onLoadMore");
				// Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                // or customLoadMoreDataFromApi(totalItemsCount); 
				customLoadMoreDataFromApi(page); 
			}
    	});
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.mainmenu, menu);
    	return super.onCreateOptionsMenu(menu);
    }


    // Click on Settings button on Action Bar and send the previously selected settings on relaunch
    public void onSettingClick(MenuItem mi){
    	// Construct intent to launch Settings Screen
    	Intent i = new Intent(this, SettingsActivity.class);
//		if (onFreshLaunch == true)
    	{
    		i.putExtra("imageSearchOptions", searchSettings);
    	}
		i.putExtra("onFreshLaunch", onFreshLaunch);
    	// Launch Settings View
		startActivityForResult(i, REQUEST_CODE);
//    	onFreshLaunch = false;
    }    
    
    // Handle user settings 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent retIntent) {
    	if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
    		// Extract name value from result extras  
    		boolean isSettingChanged = retIntent.getExtras().getBoolean("isSettingChanged");
    		if (isSettingChanged  == true)
    		{
    			SearchOptionSettings retSearchSettings = (SearchOptionSettings)retIntent.getSerializableExtra("returnedSetting");
    			if (retSearchSettings != null)
    			{
    				searchAPIParamsObj.setFromSearchSettingsObj(retSearchSettings);
    				//TODO: Save Settings to persistence
    			}
    			searchSettings.argFileType = retSearchSettings.argFileType;
    			searchSettings.argImageColor = retSearchSettings.argImageColor;
    			searchSettings.argImageSize = retSearchSettings.argImageSize;
    			searchSettings.argImageType = retSearchSettings.argImageType;
    			searchSettings.argSiteSearch = retSearchSettings.argSiteSearch;
//    			writeItems();
    			
    			mResOffset = 0;
    			prevQueryString = "";
    			aImageResults.clear();
    			makeNewNetworkReq();
    		}
      }
//      onFreshLaunch = false;
    }


    // Click on Search button
    public void onSearchClick(View v) {
    	// Make Network Request
    	makeNewNetworkReq();
       	return ;
	}
    
//    private void writeItems ()
//	{
//		File fileDir = getFilesDir();
//		File settingsFile = new File(fileDir, "settings.txt");
//		try {
//			FileUtils.writeStringToFile(settingsFile, searchSettings.toJsonString());
//			System.out.println("Write : settingString : "+ searchSettings.toJsonString());
////			FileUtils.writeItems(settingsFile, searchSettings);
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//    private void readItems()
//	{
//		File fileDir = getFilesDir();
//		File settingsFile = new File(fileDir, "settings.txt");
//		String settingString = ""; 
//		try {
//			settingString = FileUtils.readFileToString(settingsFile);
//			System.out.println("Read : settingString : " + settingString);
//			searchSettings.fromJsonString(settingString);
//		} catch (IOException e) {
//			e.printStackTrace();
////			todoItems = new ArrayList<String>();
//		}
//	}
    
    private void makeNewNetworkReq()
    {
    	System.out.println("makeNewNetworkReq");
    	String strSearchQuery = etSearchQuery.getText().toString();
    	mFreshSearch = false;

    	if (strSearchQuery.equals("") == true)
		{
			Toast.makeText(this, R.string.error_no_query, Toast.LENGTH_SHORT).show();
			return;
		}
		if (strSearchQuery.equalsIgnoreCase(prevQueryString ) == false)
		{
			prevQueryString = strSearchQuery;
			mResOffset = 0;
			mFreshSearch = true;
		}
		else
			mResOffset += mResPerPage;
    	System.out.println("Button Click : Query : " + strSearchQuery);
    	String searchUrl = searchAPIParamsObj.createUrl(strSearchQuery, mResPerPage, mResOffset);
    	System.out.println("Url: " + searchUrl);
    	
    	Boolean isInternetAvailable = isNetworkAvailable();
    	if (isInternetAvailable == false) {
    		Toast.makeText(getBaseContext(), "No Internet Connection ", Toast.LENGTH_SHORT).show();
    		return;
    	}

		AsyncHttpClient client = new AsyncHttpClient();
		System.out.println("Encoded URL : " + searchUrl);
		client.get(searchUrl, new JsonHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				System.out.println("onFailure : statusCode : " + statusCode);
				Toast.makeText(getBaseContext(), "Response Error : "+ responseString, Toast.LENGTH_SHORT).show();
				if (responseString != "")
					System.out.println("onFailure : Response : " + responseString.toString());
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				System.out.println("onFailure Error : statusCode : " + statusCode);
				Toast.makeText(getBaseContext(), "Error : "+ throwable.getMessage(), Toast.LENGTH_SHORT).show();
				if (errorResponse!= null)
					System.out.println("onFailure Error : ErrorResponse : " + errorResponse.toString());
			}
			

			@Override
			public void onRetry(int retryNo) {
				// TODO Auto-generated method stub
				super.onRetry(retryNo);
			}
			
			// This method probably sends out a network request and appends new data items to your adapter. 
	    	// Use the offset value and add it as a parameter to your API request to retrieve paginated data.
	    	// Deserialize API response and then construct new objects to append to the adapter
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.d("Debug", response.toString());
				JSONArray imageResultsJson = null;
				try {
					// Error Handling - {"responseData":null,"responseStatus":400,"responseDetails":"out of range start"}
					System.out.println("statusCode : "+ statusCode);
					int HttpResponseCode = response.getInt("responseStatus");
					if (HttpResponseCode == 400) {
						Toast.makeText(getBaseContext(), "No More Data Available", Toast.LENGTH_SHORT).show();
						return;
					} else if (HttpResponseCode != 200) {
						Toast.makeText(getBaseContext(), "Unable to process request this time", Toast.LENGTH_SHORT).show();
						return;
					}
					imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
					if (mFreshSearch == true) {
						// New Search 
						aImageResults.clear();
					}
					aImageResults.addAll(ImageResult.fromJsonArray(imageResultsJson));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				super.onSuccess(statusCode, headers, response);
			}
		});
    }

    // Check if network connection is available
    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager  = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
