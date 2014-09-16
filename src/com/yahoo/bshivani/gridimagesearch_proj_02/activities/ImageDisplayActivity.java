package com.yahoo.bshivani.gridimagesearch_proj_02.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yahoo.bshivani.gridimagesearch_proj_02.R;
import com.yahoo.bshivani.gridimagesearch_proj_02.models.ImageResult;

public class ImageDisplayActivity extends Activity {
	ImageView ivFullImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		// Pull out url from intent
		ImageResult imgItem = (ImageResult)getIntent().getSerializableExtra("imageItem");
		String url = imgItem.fullUrl;
		String  title = imgItem.title;
		getActionBar().setTitle(Html.fromHtml(title));
		// find the image view
		ivFullImage = (ImageView) findViewById(R.id.ivFullImageResult);
		// load the image url into imageview using picasso
		Picasso.with(this).load(url).into(ivFullImage);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
