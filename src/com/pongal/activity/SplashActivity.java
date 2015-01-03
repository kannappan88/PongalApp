package com.pongal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.pongal.R;

public class SplashActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_splash_layout);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				launchHomeScreen();
			}
		}, 1000);
	}

	/**
	 * launch Home screen
	 * 
	 */
	private void launchHomeScreen() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}
}
