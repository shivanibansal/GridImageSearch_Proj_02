package com.yahoo.bshivani.gridimagesearch_proj_02.adapters;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yahoo.bshivani.gridimagesearch_proj_02.R;
import com.yahoo.bshivani.gridimagesearch_proj_02.models.ImageResult;

public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultsAdapter(Context context, List<ImageResult> images) {
		super(context, android.R.layout.simple_list_item_1, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageItem = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
		}
		ImageView image = (ImageView) convertView.findViewById(R.id.ivItemImage);
		TextView  title = (TextView) convertView.findViewById(R.id.tvItemTitle);
		// clear out image from last time
		image.setImageResource(0);
		title.setText(Html.fromHtml(imageItem.title));
		// Remotely download the image data in the background (with picaso)
		Picasso.with(getContext()).load(imageItem.tbUrl).into(image);
		return convertView;
	}
}
