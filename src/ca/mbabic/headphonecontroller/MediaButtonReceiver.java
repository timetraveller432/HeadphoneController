package ca.mbabic.headphonecontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;
import ca.mbabic.headphonecontroller.statemachine.HCStateMachine;

public class MediaButtonReceiver extends BroadcastReceiver {

	private final String LOG_TAG = ".MediaButtonReceiver";

	private static HCStateMachine stateMachine = HCStateMachine.getInstance();
	
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
		
		switch(event.getKeyCode()) {
		case KeyEvent.KEYCODE_HEADSETHOOK:
			Log.i(LOG_TAG, "event.getAction() = " + event.getAction());
			if (event.getAction() == KeyEvent.ACTION_UP) {
				
				stateMachine.buttonPress(currentTime);
				
			}
		
		}
		
		setResultData(null);
		abortBroadcast();
	}

}
