package ca.mbabic.headphonecontroller.statemachine;

import android.util.Log;
import ca.mbabic.headphonecontroller.commands.CommandExecutor;

public class FourPressState extends HCState {
	private static final String TAG = ".statemachine.ThreePressState";

	public FourPressState() {
		isTerminal = true;
		nextState = null;
		executor = CommandExecutor.getInstance();
	}
	
	@Override
	public void executeCommand() {
		Log.i(TAG, "Executing FourPressState command.");
		executor.executeCommandForState(FourPressState.class);;

	}
}
