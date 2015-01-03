package com.pongal.activity.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pongal.R;
import com.pongal.util.Constants;
import com.pongal.util.Utility;

/**
 * 
 * 
 * @author Kannappan
 * 
 */
public class JallikattuFragment extends BaseFragment {
	private Activity mActivity;
	private ImageView imageryImageView;
	private TextView descriptionTextView;
	private ScaleGestureDetector scaleGestureDetector;

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
		return inflater.inflate(R.layout.fragment_jallikattu_layout, container,
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
		imageryImageView = (ImageView) view.findViewById(R.id.brand_image_view);
		imageryImageView.setImageResource(R.drawable.ic_jallikattu_bg);

		TextView titleTextview = (TextView) view
				.findViewById(R.id.title_textview);

		descriptionTextView = (TextView) view
				.findViewById(R.id.description_textview);
		Typeface font1 = Typeface.createFromAsset(mActivity.getAssets(),
				"vijayab.ttf");
		descriptionTextView.setTypeface(font1);

		descriptionTextView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				scaleGestureDetector.onTouchEvent(event);
				return true;
			}
		});
		//
		titleTextview.setTypeface(font1);
		titleTextview.setText(getResources()
				.getString(R.string.jallikattu_desc));

		scaleGestureDetector = new ScaleGestureDetector(mActivity,
				new OnSimpleScaleGestureListener());
	}

	class ReadAssetFileTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			String response = Utility
					.readAssetFile(Constants.JALLIKATTU_TEXT_FILE_NAME);
			return response;
		}

		@Override
		protected void onPostExecute(String response) {
			handleResponseData(response);
		}

	}

	/**
	 * 
	 * 
	 * @param response
	 */
	private void handleResponseData(String response) {
		descriptionTextView.setText(response);
	}

	private class OnSimpleScaleGestureListener extends
			SimpleOnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			float size = descriptionTextView.getTextSize();
			if (Constants.DEBUG) {
				Log.d("TextSizeStart", String.valueOf(size));
			}

			float factor = detector.getScaleFactor();
			if (Constants.DEBUG) {
				Log.d("Factor", String.valueOf(factor));
			}

			float product = size * factor;
			if (Constants.DEBUG) {
				Log.d("TextSize", String.valueOf(product));
			}
			descriptionTextView
					.setTextSize(TypedValue.COMPLEX_UNIT_PX, product);

			size = descriptionTextView.getTextSize();
			if (Constants.DEBUG) {
				Log.d("TextSizeEnd", String.valueOf(size));
			}
			return true;
		}
	}
}
