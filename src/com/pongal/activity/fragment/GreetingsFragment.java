package com.pongal.activity.fragment;

import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.pongal.R;

/**
 * 
 * 
 * @author Kannappan
 * 
 */
public class GreetingsFragment extends BaseFragment {
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
		return inflater.inflate(R.layout.fragment_greetings_layout, container,
				false);
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
	@SuppressWarnings("deprecation")
	private void initializeView(final View view) {
		// Note that Gallery view is deprecated in Android 4.1---
		Gallery gallery = (Gallery) view.findViewById(R.id.gallery);
		// display the images selected
		final ImageView imageView = (ImageView) view
				.findViewById(R.id.image_view);
		
		gallery.setSelected(true);
		gallery.setSpacing(2);
		gallery.setAdapter(new GalleryImageAdapter(mActivity));
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				imageView.setImageResource(mImageIds[position]);
			}
		});
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Bitmap icon = BitmapFactory.decodeResource(getResources(),
						mImageIds[position]);
				Bitmap scaledBitmap = scaleBitmap(icon);
				imageView.setImageBitmap(scaledBitmap);

				//
				//setWallpaper(scaledBitmap);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	// the images to display
	Integer[] mImageIds = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e,

			R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i,
			R.drawable.j,

			R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n,
			R.drawable.p,

			R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t,
			R.drawable.u,

			R.drawable.v, R.drawable.w, R.drawable.y, R.drawable.z,
			R.drawable.aa, R.drawable.ab, R.drawable.ac, R.drawable.ad,

			R.drawable.ae, R.drawable.af, R.drawable.ag

	};

	public class GalleryImageAdapter extends BaseAdapter {
		private Context mContext;

		public GalleryImageAdapter(Context context) {
			mContext = context;
		}

		public int getCount() {
			return mImageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		// Override this method according to your need
		public View getView(int index, View view, ViewGroup viewGroup) {
			// TODO Auto-generated method stub
			ImageView i = new ImageView(mContext);

			i.setImageResource(mImageIds[index]);
			i.setLayoutParams(new Gallery.LayoutParams(400, 400));

			i.setScaleType(ImageView.ScaleType.FIT_XY);

			return i;
		}
	}

	/**
	 * scaling a bitmap..
	 * 
	 * @param bmp
	 * @return
	 */
	private Bitmap scaleBitmap(Bitmap bmp) {
		final int maxSize = 960;
		int outWidth;
		int outHeight;
		int inWidth = bmp.getWidth();
		int inHeight = bmp.getHeight();
		if (inWidth > inHeight) {
			outWidth = maxSize;
			outHeight = (inHeight * maxSize) / inWidth;
		} else {
			outHeight = maxSize;
			outWidth = (inWidth * maxSize) / inHeight;
		}

		Bitmap resizedBitmap = Bitmap.createScaledBitmap(bmp, outWidth,
				outHeight, false);
		return resizedBitmap;
	}

	/**
	 * get screen width..
	 * 
	 * @param context
	 * @return
	 */
	public static int getDeviceScreenWidth(Activity context) {
		Point size = new Point();
		int width;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getSize(size);
			width = size.x;
		} else {
			width = ((Activity) context).getWindowManager().getDefaultDisplay()
					.getWidth();
		}
		return width;
	}

	/**
	 * get screen width..
	 * 
	 * @param context
	 * @return
	 */
	public static int getDeviceScreenHeight(Activity context) {
		Point size = new Point();
		int width;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getSize(size);
			width = size.y;
		} else {
			width = ((Activity) context).getWindowManager().getDefaultDisplay()
					.getHeight();
		}
		return width;
	}

	private void setWallpaper(Bitmap scaledBitmap) {
		WallpaperManager myWallpaperManager = WallpaperManager
				.getInstance(mActivity);
		try {
			 Bitmap resized = Bitmap.createScaledBitmap(scaledBitmap,
			 getDeviceScreenWidth(mActivity) - 100,
			 getDeviceScreenHeight(mActivity), true);
			myWallpaperManager.setBitmap(resized);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
