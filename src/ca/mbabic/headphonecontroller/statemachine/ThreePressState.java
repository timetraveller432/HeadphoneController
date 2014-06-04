/*
 * TODO: license
 */
package ca.mbabic.headphonecontroller.statemachine;

import android.util.Log;
import ca.mbabic.headphonecontroller.commands.HCCommandContext;
import ca.mbabic.headphonecontroller.commands.PreviousCommand;
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
		commandContext = new HCCommandContext();
		commandContext.setCommand(new PreviousCommand());
	}
	
	@Override
	public void executeCommand() {
		Log.i(TAG, "Executing ThreePressState command.");
		commandContext.execute();

	}

}
