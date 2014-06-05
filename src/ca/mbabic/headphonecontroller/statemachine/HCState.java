/*
 * TODO: license
 */
package ca.mbabic.headphonecontroller.statemachine;

import ca.mbabic.headphonecontroller.commands.CommandExecutor;
import ca.mbabic.headphonecontroller.commands.HCCommand;
import ca.mbabic.headphonecontroller.commands.HCCommandContext;

/**
 * Class representing a state in the HCStateMachine.
 * Implementation of State Pattern.
 * See: http://sourcemaking.com/design_patterns/state
 * @author Marko Babic
 */
public abstract class HCState {
	/**
	 * Reference to CommandExecutor used to executre correct command for the
	 * state at run time.
	 */
	protected CommandExecutor executor;

	protected HCState nextState = null;
	protected boolean isTerminal = false;
	
	// Method to be implemented for now
	public abstract void executeCommand();	
	
	public HCState getNextState() {
		return nextState;
	};
	
	public boolean isTerminal() {
		return isTerminal;
	}
	
	

}
