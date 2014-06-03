/*
 * TODO: license
 */

package ca.mbabic.headphonecontroller.statemachine;

/**
 * State machine keeping track of the state of the button presses.
 * @author Marko Babic
 */
public class HCStateMachine {

	// TODO: make configurable
	private static final long BTN_PRESS_INTERVAL = 400;
	
	/**
	 * Instance of HCStateMachine available to program.
	 */
	private static volatile HCStateMachine instance = null;
	
	private HCStateMachine() {
		
	}
	
	public static HCStateMachine getInstance() {
		if (instance == null) {
			synchronized (HCStateMachine.class) {
				// Double check instance locking
				if (instance == null) {
					instance = new HCStateMachine();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Indicate to state machine that the media button has been pressed on
	 * the headphones.
	 * @param pressTime
	 * 		The time at which the button was pressed.
	 */
	public void buttonPress(long pressTime) {
		
		
		
	}
	
}
