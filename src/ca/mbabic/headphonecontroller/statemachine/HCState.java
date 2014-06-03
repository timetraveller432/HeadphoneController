package ca.mbabic.headphonecontroller.statemachine;

public abstract class HCState {
	/**
	 * Command/reference to command to be executed when state machine is in
	 * this given state.
	 */
	// TODO
	
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
