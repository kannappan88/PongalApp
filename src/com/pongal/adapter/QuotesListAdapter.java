package com.pongal.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pongal.R;
import com.pongal.model.QuotesData.QuotesList;

public class QuotesListAdapter extends BaseAdapter {

	private static final String TAG = QuotesListAdapter.class.getSimpleName();
	private Context context;
	private List<QuotesList> mDataModel = new ArrayList<QuotesList>();

	public QuotesListAdapter(Context context, List<QuotesList> list) {
		this.context = context;
		this.mDataModel = list;
	}

	@Override
	public int getCount() {
		return mDataModel.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView = null;
		if (convertView != null) {
			gridView = convertView;
		} else {
			gridView = inflater.inflate(R.layout.list_quotes_layout, parent,
					false);
		}

		// Textview
		TextView titleTextView = (TextView) gridView
				.findViewById(R.id.quotes_textview);
		ImageView shareImageView = (ImageView) gridView
				.findViewById(R.id.share_imageview);
		shareImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				QuotesList issuesModel = (QuotesList) mDataModel.get(position);
				if (issuesModel != null) {
					handleShareButton(issuesModel.getMessage());
				}
			}
		});
		//
		QuotesList issuesModel = (QuotesList) mDataModel.get(position);
		if (issuesModel != null) {
			titleTextView.setText(issuesModel.getMessage());
		}

		return gridView;
	}

	protected void handleShareButton(String message) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, message);
		context.startActivity(Intent.createChooser(intent, "Share Quotes"));
	}

}
