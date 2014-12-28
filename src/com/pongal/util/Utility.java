package com.pongal.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import com.pongal.BaseApplication;

public class Utility {

	/**
	 * read file data from asset folder
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readAssetFile(String fileName) {
		String response = null;
		try {
			InputStream stream = BaseApplication.getApplication().getAssets()
					.open(fileName);
			int size = stream.available();
			byte[] buffer = new byte[size];
			stream.read(buffer);
			stream.close();
			response = new String(buffer);
		} catch (IOException e) {
			// Handle exceptions here
		}
		return response;
	}

	/**
	 * Change drawable color
	 * 
	 * @return
	 */
	public static Drawable changeDrawableColor(int color, int resource,
			Context context) {
		final Drawable drawable = context.getResources().getDrawable(resource);

		drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);

		return drawable;
	}

	// try {
	// InputStream stream = BaseApplication.getApplication().getAssets()
	// .open(fileName);
	// Reader decoded = new InputStreamReader(stream,
	// "UTF-8");
	// char[] charBuffer = new char[1024];
	// while (true) {
	// int n = decoded.read(charBuffer);
	// if (n < 0)
	// break;
	// buffer.append(buffer, 0, n);
	// }
	// response = new String(buffer.toString());
	// } catch (IOException e) {
	// // Handle exceptions here
	// }
}
