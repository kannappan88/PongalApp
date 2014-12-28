package com.pongal;

import android.app.Application;

public class BaseApplication extends Application {

	private static BaseApplication application;

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	/**
	 * create a application instance
	 * 
	 */
	private void init() {
		if (application == null) {
			application = BaseApplication.this;
		}
	}

	/**
	 * get Application context from Application class
	 * 
	 * @return
	 */
	public static BaseApplication getApplication() {
		return application;
	}
}
