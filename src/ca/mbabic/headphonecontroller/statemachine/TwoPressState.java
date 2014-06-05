package ca.mbabic.headphonecontroller.statemachine;

import android.util.Log;
import ca.mbabic.headphonecontroller.commands.CommandExecutor;
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
		isTerminal = false;
		nextState = new ThreePressState();
		executor = CommandExecutor.getInstance();
	}

	@Override
	public void executeCommand() {
		Log.i(TAG, "Executing TwoPressState command.");
		executor.executeCommandForState(TwoPressState.class);;
	}
	
	@Override
	public HCState getNextState() {
		return nextState;
	}
}
