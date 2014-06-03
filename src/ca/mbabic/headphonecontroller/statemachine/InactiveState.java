package ca.mbabic.headphonecontroller.statemachine;

public class InactiveState extends HCState {


	public InactiveState() {
		isActive = false;
		isTerminal = false;
		nextState = new OnePressState();
	}
	
	
	@Override
	public void executeCommand() {

	}
	
}
