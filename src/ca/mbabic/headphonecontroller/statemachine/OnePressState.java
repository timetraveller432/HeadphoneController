/*
 * TODO: license
 */
package ca.mbabic.headphonecontroller.statemachine;

import android.util.Log;
import android.widget.Toast;

/**
 * State representing the media button having been clicked exactly once since
 * the last command execution/interval expiration.
 * @author Marko Babic
 *
 */
public class OnePressState extends HCState {

	private static final String TAG = ".statemachine.OnePressState";
	@Override
	public void executeCommand() {

		Log.i(TAG, "Executing OnePressState command.");
				
	}

	
	
}
