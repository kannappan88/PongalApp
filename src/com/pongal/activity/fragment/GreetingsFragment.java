package com.pongal.activity.fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	private ImageView mImageView;
	private int mPosition;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
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

		// for (int i = 0; i < mImageIds.length; i++) {
		//
		// Bitmap icon = BitmapFactory.decodeResource(getResources(),
		// mImageIds[i]);
		//
		// try {
		// saveImageToExternalStorage(icon, "Pongal" + i + ".jpg");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		//
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
		mImageView = (ImageView) view.findViewById(R.id.image_view);

		gallery.setSelected(true);
		gallery.setSpacing(2);
		gallery.setAdapter(new GalleryImageAdapter(mActivity));
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				mPosition = position;
				mImageView.setImageResource(mImageIds[position]);
			}
		});
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mPosition = position;
				Bitmap icon = BitmapFactory.decodeResource(getResources(),
						mImageIds[position]);
				Bitmap scaledBitmap = scaleBitmap(icon);
				mImageView.setImageBitmap(scaledBitmap);

				// setWallpaper(scaledBitmap);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	// the images to display
	Integer[] mImageIds = { R.drawable.as, R.drawable.ab, R.drawable.ac,
			R.drawable.ad, R.drawable.ic_home_bg, R.drawable.ic_jallikattu_bg,

			R.drawable.ae, R.drawable.af, R.drawable.ag, R.drawable.ah,
			R.drawable.ai, R.drawable.aj, R.drawable.ak,

			R.drawable.al, R.drawable.am, R.drawable.an, R.drawable.ao,
			R.drawable.ap, R.drawable.aq, R.drawable.ar,

			R.drawable.at, R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e,

			R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i,
			R.drawable.j,

			R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n,
			R.drawable.p,

			R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t,
			R.drawable.u,

			R.drawable.v, R.drawable.w, R.drawable.y, R.drawable.z,
			R.drawable.aa

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
			i.setLayoutParams(new Gallery.LayoutParams(250, 250));

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

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		mActivity.getMenuInflater().inflate(R.menu.home, menu);

		MenuItem menuItem = menu.findItem(R.id.action_share);
		MenuItemCompat.setShowAsAction(menuItem,
				MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_share:
			handleShaeIntent();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void handleShaeIntent() {
		// TODO Auto-generated method stub
		getDefaultIntent();
	}

	/**
	 * Defines a default (dummy) share intent to initialize the action provider.
	 * However, as soon as the actual content to be used in the intent is known
	 * or changes, you must update the share intent by again calling
	 * mShareActionProvider.setShareIntent()
	 */
	private void getDefaultIntent() {

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				mImageIds[mPosition]);
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("image/jpeg");

		try {
			saveImageToExternalStorage(bitmap, "Pongal" + mPosition + ".jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}

		File externalFileStorage = Environment.getExternalStorageDirectory();
		File directory = new File(externalFileStorage.getAbsolutePath()
				+ "/Pongal/" + "Pongal" + mPosition + ".jpg");

		Uri uri = Uri.fromFile(directory);
		share.putExtra(Intent.EXTRA_STREAM, uri);

		startActivity(Intent.createChooser(share, "Share Image"));
	}

	public static boolean checkSDcardAvailable() {
		boolean isSdcardAvailable = false;
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			isSdcardAvailable = true;
		}
		return isSdcardAvailable;
	}

	public static boolean saveImageToExternalStorage(Bitmap bitmap,
			String fileName) throws IOException {
		boolean isSuccess = false;
		if (checkSDcardAvailable()) {
			FileOutputStream fileOutputStream = null;
			try {
				File externalFileStorage = Environment
						.getExternalStorageDirectory();
				File directory = new File(externalFileStorage.getAbsolutePath()
						+ "/Pongal");
				directory.mkdirs();
				File imageFile = new File(directory, fileName);
				if (imageFile.exists()) {
					imageFile.delete();
					imageFile.createNewFile();
				}

				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
						byteArrayOutputStream);
				byte[] byteArray = byteArrayOutputStream.toByteArray();
				fileOutputStream = new FileOutputStream(imageFile);
				if (fileOutputStream != null) {
					fileOutputStream.write(byteArray);
					fileOutputStream.flush();
					fileOutputStream.close();
				}
				isSuccess = true;
			} catch (FileNotFoundException e) {
				if (com.pongal.util.Constants.DEBUG) {
					e.printStackTrace();
				}
				isSuccess = false;
			}
		}
		return isSuccess;
	}

}
