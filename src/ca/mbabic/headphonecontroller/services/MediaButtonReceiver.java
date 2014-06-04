package ca.mbabic.headphonecontroller.services;

import ca.mbabic.headphonecontroller.statemachine.HCStateMachine;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Receives presses of the media button and invokes appropriate HCStateMachine
 * methods.
 * @author Marko Babic
 */
public class MediaButtonReceiver extends BroadcastReceiver {

	/**
	 * Logging tag.
	 */
	private final String TAG = ".services.MediaButtonReceiver";

	/**
	 * Reference to state machine keeping track of time elapsed between key
	 * presses.
	 */
	private static HCStateMachine stateMachine = HCStateMachine.getInstance();

	@Override
	public void onReceive(Context cxt, Intent intent) {

		KeyEvent event;
		int action;
		
		// Ensure that we have received the intent action of the type
		// we expected.
		if (!Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
			Log.e(TAG,
					"Received intent action type = " + intent.getAction());
			return;
		}

		event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

		if (event == null) {
			Log.e(TAG, "Received intent with null key event!");
			return;
		}

		action = event.getAction();
		
		switch (event.getKeyCode()) {
		case KeyEvent.KEYCODE_HEADSETHOOK:
			
			if (action == KeyEvent.ACTION_UP) {
				
				stateMachine.keyUp();
				
			} else if (action == KeyEvent.ACTION_DOWN) {
				
				Log.d(TAG, "KEYDOWN ACTION RECEIVED.");
				
			}

		}

		// Stop other apps from seeing the event and interfering with our
		// actions.
		setResultData(null);
		abortBroadcast();
	}

}
