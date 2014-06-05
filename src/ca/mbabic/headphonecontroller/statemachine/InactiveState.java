package ca.mbabic.headphonecontroller.statemachine;

public class InactiveState extends HCState {


	public InactiveState() {
		isTerminal = false;
		nextState = new OnePressState();
	}
	
	
	@Override
	public void executeCommand() {

	}
	
}
