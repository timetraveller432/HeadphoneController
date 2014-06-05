package ca.mbabic.headphonecontroller.commands;

import ca.mbabic.headphonecontroller.configuration.HCConfigAdapter;

/**
 * Determines at run time the correct command to run for a particular state and
 * executes the command as appropriate.
 * 
 * @author Marko Babic
 * 
 */
public class CommandExecutor {

	private HCConfigAdapter adapter;
	
	public CommandExecutor(HCConfigAdapter configAdapter) {
		adapter = configAdapter;
	}
	
	public void executeCommandForState(Class state) {
		
		adapter.getCommandContext(state).execute();		
	
	}

}
