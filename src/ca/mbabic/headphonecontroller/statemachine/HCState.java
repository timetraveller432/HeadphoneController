/*
 * TODO: license
 */
package ca.mbabic.headphonecontroller.statemachine;

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
	 * Command/reference to command to be executed when state machine is in
	 * this given state.
	 */
	// TODO
	protected HCCommandContext commandContext = new HCCommandContext();
	protected HCState nextState = null;
	protected boolean isActive = false;
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
