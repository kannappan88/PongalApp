package com.pongal.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.Window;

import com.pongal.R;
import com.pongal.activity.fragment.GreetingsFragment;
import com.pongal.activity.fragment.HomeFragment;

public class GreetingsActivity extends BaseActivity {
	private static final String TAG = GreetingsActivity.class.getSimpleName();

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_layout);
		if (findViewById(R.id.fragment_container) != null) {
			if (savedInstanceState != null) {
				return;
			}
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			GreetingsFragment fragment = new GreetingsFragment();
			fragmentTransaction.add(R.id.fragment_container, fragment);
			fragmentTransaction.commit();
		}
		isShowActionBarHomeButton(true);
	}

	/**
	 * handle Home up button click event
	 * 
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
