package com.pongal.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pongal.BaseApplication;
import com.pongal.util.Constants;

public class Connector {

	private static final String GET = "GET";
	private static final String ACCEPT_TYPE = "Accept";
	private static final String ACCEPT_TYPE_LANGUAGE = "Accept-Language";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String CONTENT_TYPE_JSON = "application/json";
	private static final String CONTENT_TYPE_JSON_UTF = "application/json;charset=UTF-8";
	private static final String CONTENT_TYPE_XML_UTF = "application/xml;charset=UTF-8";
	private static final String CONTENT_TYPE_XML = "application/xml";
	private static final String CONTENT_TYPE_PDF = "application/pdf";
	private static final String CONTENT_TYPE_PNG = "image/png";
	private static final String TAG = Connector.class.getSimpleName();
	private static final int OPERATION_TIMEOUT = 60 * 1000;
	// build a GSON
	private static Gson gson = new GsonBuilder().create();

	/**
	 * getting a GSON object instance
	 * 
	 * @return
	 */
	public static Gson getGson() {
		return gson;
	}

	/**
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	/**
	 * 
	 * 
	 * @param src
	 * @param clazz
	 * @return
	 */
	public static <T> String toJson(T src, Class<T> clazz) {
		return gson.toJson(src, clazz);
	}

	public String executeRequest(String url) throws IOException {
		InputStream is = null;
		URL parsedUrl = new URL(url);
		HttpURLConnection connection = openConnection(parsedUrl);

		connection.setRequestProperty(ACCEPT_TYPE, CONTENT_TYPE_JSON);
		connection.setRequestMethod(GET);
		// Starts the query
		connection.connect();
		// get response code.
		int responseCode = connection.getResponseCode();
		if (Constants.DEBUG) {
			Log.d(TAG, "The response code : " + responseCode);
		}

		is = connection.getInputStream();
		// Convert the InputStream into a string
		String responseData = convertStreamToString(is);

		if (Constants.DEBUG) {
			Log.d(TAG, "response:" + responseData);
		}
		return responseData;
	}

	/**
	 * Convert a stream to string
	 * 
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private String convertStreamToString(InputStream is) throws IOException {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (String line; (line = reader.readLine()) != null;) {
				sb.append(line);
				sb.append("\n");
			}

			return sb.toString();
		} finally {
			if (reader != null) {
				reader.close();
			}

			is.close();
		}
	}

	/**
	 * Opens an {@link HttpURLConnection} with parameters.
	 * 
	 * @param url
	 * @return an open connection
	 * @throws IOException
	 */
	private HttpURLConnection openConnection(URL url) throws IOException {
		HttpURLConnection connection = createConnection(url);

		connection.setConnectTimeout(OPERATION_TIMEOUT);
		connection.setReadTimeout(OPERATION_TIMEOUT);
		connection.setUseCaches(false);
		connection.setDoInput(true);

		return connection;
	}

	/**
	 * Create an {@link HttpURLConnection} for the specified {@code url}.
	 */
	protected HttpURLConnection createConnection(URL url) throws IOException {
		return (HttpURLConnection) url.openConnection();
	}
	
	/**
	 * Check whether the network is connected or not.
	 * 
	 * @return
	 */
	public static boolean isConnected() {
		final ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication
				.getApplication()
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		final NetworkInfo wifi = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		final NetworkInfo mobile = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean online = false;
		if (wifi.isAvailable() || mobile.isAvailable()) {
			if (connectivityManager.getActiveNetworkInfo() != null) {
				online = connectivityManager.getActiveNetworkInfo()
						.isConnectedOrConnecting();
			}
		}
		return online;
	}

}
