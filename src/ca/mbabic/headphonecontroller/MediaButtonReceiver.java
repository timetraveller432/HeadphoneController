package ca.mbabic.headphonecontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MediaButtonReceiver extends BroadcastReceiver {
	
	private final String LOG_TAG = ".MediaButtonReceiver";

	@Override
	public void onReceive(Context cxt, Intent intent) {
		
		// For now, simply log the intent action and set result data.
		
		Log.i(LOG_TAG, "INTENT RECEIVED: " + intent.getAction());

		setResultData(intent.getAction());
	}
	
}
