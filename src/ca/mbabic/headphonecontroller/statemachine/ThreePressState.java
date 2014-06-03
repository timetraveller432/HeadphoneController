/*
 * TODO: license
 */
package ca.mbabic.headphonecontroller.statemachine;

import android.util.Log;
/**
 * State representing the media button having been clicked exactly thrice since
 * the last command execution/interval expiration.
 * @author Marko Babic
 *
 */
public class ThreePressState extends HCState {

	private static final String TAG = ".statemachine.ThreePressState";

	public ThreePressState() {
		isActive = true;
		isTerminal = true;
		nextState = null;
	}
	
	@Override
	public void executeCommand() {
		Log.i(TAG, "Executing ThreePressState command.");

	}

}
