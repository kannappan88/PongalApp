package com.pongal.activity.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.pongal.R;

/**
 * Base class for all fragments..
 * 
 * @author Kannappan
 * 
 */
public class BaseFragment extends Fragment {
	private static final String TEL = "tel:";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * Replace a fragments
	 * 
	 * @param targetFragment
	 * @param addToBackStack
	 * @param bundle
	 */
	protected void replaceFragments(BaseFragment targetFragment,
			boolean addToBackStack, Bundle bundle) {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		if (bundle != null) {
			targetFragment.setArguments(bundle);
		}
		fragmentTransaction.replace(R.id.fragment_container, targetFragment);
		if (!addToBackStack) {
			fragmentTransaction.addToBackStack(null);
		}
		// Commit the transaction
		fragmentTransaction.commit();
	}

	public void makeCall(String phoneNumber) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse(TEL + phoneNumber));
		startActivity(intent);
	}

}
