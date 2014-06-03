package ca.mbabic.headphonecontroller.statemachine;

import android.util.Log;

/**
 * State representing the media button having been clicked exactly once since
 * the last command execution/interval expiration.
 * @author Marko Babic
 *
 */
public class TwoPressState extends HCState {

	private static final String TAG = ".statemachine.TwoPressState";
	
	
	public TwoPressState() {
		isActive = true;
		isTerminal = false;
		nextState = new ThreePressState();
	}
	
	
	@Override
	public void executeCommand() {

		Log.i(TAG, "Executing TwoPressState command.");
				
	}
	
	@Override
	public HCState getNextState() {
		return nextState;
	}
}
