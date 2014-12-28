package com.pongal.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pongal.R;
import com.pongal.activity.GreetingsActivity;
import com.pongal.activity.JallikattuActivity;
import com.pongal.activity.PongalActivity;
import com.pongal.activity.QuotesActivity;

/**
 * 
 * 
 * @author Kannappan
 * 
 */
public class HomeFragment extends BaseFragment implements OnClickListener {
	private Activity mActivity;

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
		return inflater
				.inflate(R.layout.fragment_home_layout, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initializeView(view);
	}

	/**
	 * initialize a layout view
	 * 
	 * @param view
	 */
	private void initializeView(View view) {
		view.findViewById(R.id.title_textview).setVisibility(View.GONE);

		TextView pongalTextView = (TextView) view
				.findViewById(R.id.pongal_textview);
		pongalTextView.setOnClickListener(this);

		TextView jallikattuTextView = (TextView) view
				.findViewById(R.id.jallikattu_textview);
		jallikattuTextView.setOnClickListener(this);

		TextView greetingsTextView = (TextView) view
				.findViewById(R.id.greetings_textview);
		greetingsTextView.setOnClickListener(this);

		TextView quotesTextView = (TextView) view
				.findViewById(R.id.quotes_textview);
		quotesTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pongal_textview:
			handlePongalButtonSubmit();
			break;
		case R.id.jallikattu_textview:
			handleJallikattuButtonSubmit();
			break;
		case R.id.greetings_textview:
			handleGreetingsButtonSubmit();
			break;
		case R.id.quotes_textview:
			handleQuotesButtonSubmit();
			break;

		default:
			break;
		}
	}

	private void handleQuotesButtonSubmit() {
		Intent intent = new Intent(mActivity, QuotesActivity.class);
		startActivity(intent);
	}

	private void handleGreetingsButtonSubmit() {
		Intent intent = new Intent(mActivity, GreetingsActivity.class);
		startActivity(intent);
	}

	private void handleJallikattuButtonSubmit() {
		Intent intent = new Intent(mActivity, JallikattuActivity.class);
		startActivity(intent);
	}

	private void handlePongalButtonSubmit() {
		Intent intent = new Intent(mActivity, PongalActivity.class);
		startActivity(intent);
	}
}
