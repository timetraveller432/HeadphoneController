package ca.mbabic.headphonecontroller.commands;

import ca.mbabic.headphonecontroller.configuration.HCConfigAdapter;
import ca.mbabic.headphonecontroller.statemachine.HCStateMachine;

/**
 * Determines at run time the correct command to run for a particular state and
 * executes the command as appropriate.
 * 
 * @author Marko Babic
 * 
 */
public class CommandExecutor {

	private HCConfigAdapter adapter;
	
	private static CommandExecutor instance;
	
	private CommandExecutor(HCConfigAdapter configAdapter) {
		adapter = configAdapter;
	}
	
	public static CommandExecutor createInstance(HCConfigAdapter ca) {
		if (instance == null) {
			synchronized (CommandExecutor.class) {
				// Double check instance locking
				if (instance == null) {
					instance = new CommandExecutor(ca);
				}
			}
		}
		return instance;	
	}
	/**
	 * @return Singleton instance of CommandExecutor.
	 */
	public static CommandExecutor getInstance() {
		if (instance == null) {
			// TODO: throw error?
		}
		return instance;
	}
	
	public void executeCommandForState(Class state) {
		HCCommandContext cxt = adapter.getCommandContext(state);
		cxt.execute();		
	
	}

}
