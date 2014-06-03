package ca.mbabic.headphonecontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class MediaButtonReceiver extends BroadcastReceiver {

	private final String LOG_TAG = ".MediaButtonReceiver";

	// Max time b/w button presses before resetting state. 
	private final long PRESS_INTERVAL = 400;
	
	// TODO: will be part of state controller object.
	private static long mBtnPressTime0 = 0;
	private static long mBtnPressTime1 = 0;
	
	
	@Override
	public void onReceive(Context cxt, Intent intent) {

		KeyEvent event;
		Long currentTime;

		// Get current time.
		currentTime = SystemClock.uptimeMillis();
		
		// For now, simply log the intent action and set result data.
		Log.i(LOG_TAG, "INTENT RECEIVED: " + intent.getAction());

		// Ensure that we have received the intent action of the type
		// we expected.
		if (!Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction()))
			return;

		event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

		if (event == null) {
			Log.e(LOG_TAG, "Received intent with null key event!");
			return;
		}
		
		
		Log.i(LOG_TAG, "KEY CODE = " + event.getKeyCode());
		Log.i(LOG_TAG, "REPEAT COUNT = " + event.getRepeatCount());

		// TODO: we will eventually follow a state-machine and cmd pattern
		// to keep track of button presses.  Should also note that because
		// ReceiverObjects are destroyed as soon as they are returned from,
		// all this receiver should do is send messages to some singleton
		// state controller object and it will keep track of all the state
		switch(event.getKeyCode()) {
		case KeyEvent.KEYCODE_HEADSETHOOK:
			Log.i(LOG_TAG, "event.getAction() = " + event.getAction());
			if (event.getAction() == KeyEvent.ACTION_UP) {

				if (currentTime - mBtnPressTime0 > PRESS_INTERVAL &&
					currentTime - mBtnPressTime1 > PRESS_INTERVAL) {

					Toast.makeText(cxt, "State reset.", Toast.LENGTH_SHORT).show();
					mBtnPressTime0 = currentTime;
					
				} else if (currentTime - mBtnPressTime1 <= PRESS_INTERVAL) {
					
				}
						
				
			}
		
		}
		
		setResultData(null);
		abortBroadcast();
	}

}
