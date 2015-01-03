package com.pongal.activity.fragment;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pongal.R;
import com.pongal.adapter.QuotesListAdapter;
import com.pongal.executor.Connector;
import com.pongal.model.QuotesData;
import com.pongal.model.QuotesData.QuotesList;
import com.pongal.util.Constants;
import com.pongal.util.Utility;

/**
 * 
 * 
 * @author Kannappan
 * 
 */
public class QuotesFragment extends BaseFragment {
	private Activity mActivity;
	private ListView mListView;
	private static final String TAG = QuotesFragment.class.getSimpleName();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_quotes_layout, container,
				false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initializeView(view);

		//
		ReadAssetFileTask task = new ReadAssetFileTask();
		task.execute();

	}

	/**
	 * initialize a layout view
	 * 
	 * @param view
	 */
	private void initializeView(View view) {
		mListView = (ListView) view.findViewById(R.id.listview);
	}

	class ReadAssetFileTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			String response = Utility
					.readAssetFile(Constants.QUOTES_TEXT_FILE_NAME);
			return response;
		}

		@Override
		protected void onPostExecute(String response) {
			if (Constants.DEBUG) {
				Log.d(TAG, "response data: " + response);
			}

			handleResponseData(response);
		}

	}

	/**
	 * 
	 * 
	 * @param response
	 */
	private void handleResponseData(String response) {
		QuotesData quotesData = Connector.fromJson(response, QuotesData.class);
		if (quotesData != null) {
			List<QuotesList> list = quotesData.getQuotesList();
			QuotesListAdapter adapter = new QuotesListAdapter(mActivity, list);
			mListView.setAdapter(adapter);
		}
	}
}
