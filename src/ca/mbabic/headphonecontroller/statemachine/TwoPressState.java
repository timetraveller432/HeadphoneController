package ca.mbabic.headphonecontroller.statemachine;

import android.util.Log;
import ca.mbabic.headphonecontroller.commands.HCCommandContext;
import ca.mbabic.headphonecontroller.commands.SkipCommand;

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
		commandContext = new HCCommandContext();
		commandContext.setCommand(new SkipCommand());
	}
	
	
	@Override
	public void executeCommand() {

		Log.i(TAG, "Executing TwoPressState command.");
		commandContext.execute();
				
	}
	
	@Override
	public HCState getNextState() {
		return nextState;
	}
}
