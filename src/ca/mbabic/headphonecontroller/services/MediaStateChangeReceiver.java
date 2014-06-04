package ca.mbabic.headphonecontroller.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MediaStateChangeReceiver extends BroadcastReceiver {

	private static final String TAG = ".services.MediaStateChangeReceiver";
	
	private static MediaButtonListenerService mListenerService = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {

		String action;
		
		action = intent.getAction();
		
		Log.d(TAG, "Received broadcast.");
		
		if (action.equals("com.android.music.playstatechanged")) {
			if (mListenerService != null) {
				mListenerService.registerAsMediaButtonListener();
			}
		}
	}
	
	public void setService(MediaButtonListenerService mbls) {
		mListenerService = mbls;
	}

}
