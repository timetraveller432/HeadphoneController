/*
 * TODO: license
 */
package ca.mbabic.headphonecontroller.statemachine;

import android.util.Log;
import ca.mbabic.headphonecontroller.commands.HCCommandContext;
import ca.mbabic.headphonecontroller.commands.PlayPauseCommand;

/**
 * State representing the media button having been clicked exactly once since
 * the last command execution/interval expiration.
 * @author Marko Babic
 *
 */
public class OnePressState extends HCState {

	
	public OnePressState() {
		isActive = true;
		isTerminal = false;
		nextState = new TwoPressState();
		commandContext = new HCCommandContext();
		commandContext.setCommand(new PlayPauseCommand());
	}
	
	private static final String TAG = ".statemachine.OnePressState";
	@Override
	public void executeCommand() {

		Log.i(TAG, "Executing OnePressState command.");
		commandContext.execute();
				
	}
	
	
	
}
