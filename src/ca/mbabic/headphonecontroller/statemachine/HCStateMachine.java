/*
 * TODO: license
 */

package ca.mbabic.headphonecontroller.statemachine;

import java.util.concurrent.Semaphore;

import android.os.SystemClock;
import android.util.Log;

/**
 * State machine keeping track of the state of the button presses.
 * @author Marko Babic
 */
public class HCStateMachine {

	private static final String TAG = ".statemachine.HCStateMachine";
	
	// TODO: make configurable
	private static final long BTN_PRESS_INTERVAL = 400;
	
	/**
	 * Instance of HCStateMachine available to the application.
	 */
	private static volatile HCStateMachine instance = null;
	
	private static HCState currentState;
	private static HCState startState = new InactiveState();
	
	private static long timeToExecution;
	private static final long SLEEP_INTERVAL = 100;
	private static boolean isCountingDown = false;
	private static Semaphore countdownSemaphore = new Semaphore(1);
	private Thread countdownThread = new Thread(countdownRunnable);
	
	static Runnable countdownRunnable = new Runnable() {
		
		public void run() {
			
			long startTime = SystemClock.uptimeMillis();
			
			isCountingDown = true;
			timeToExecution = BTN_PRESS_INTERVAL;
			
			Log.i(TAG, "CountdownThread started!");
			
			while (timeToExecution > 0) {
			
				try {
					Thread.sleep(SLEEP_INTERVAL);
					countdownSemaphore.acquire();
					timeToExecution -= SLEEP_INTERVAL;
					Log.i(TAG, "CountdownThread timeToExecution: " + timeToExecution);
				} catch (InterruptedException ie) {
					
					Log.e(TAG, "Coundown thread interrupted!");
					isCountingDown = false;
					return;
					
				} finally {
					countdownSemaphore.release();
				}
				
			}
			
			// Countdown expired, execute current state's command.
			currentState.executeCommand();
			currentState = startState;
			isCountingDown = false;
		}
		
	};
	
	
	private HCStateMachine() {
		currentState = startState;
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
	 * TODO: should _always_ result in state transition to next
	 * state?? (probably yes)
	 * @param pressTime
	 * 		The time at which the button was pressed.
	 */
	public void buttonPress(long pressTime) {
		makeStateTransition();
	}
	
	private void makeStateTransition() {
		
		HCState nextState = currentState.getNextState();
		
		if (currentState.isTerminal()) {
			currentState = startState;
			stopCountdownToExecution();
			currentState.executeCommand();
		} else {
			currentState = nextState;
			startCountdownToExecution();
		}
	}
	
	private void startCountdownToExecution() {
		
		try {
			
			countdownSemaphore.acquire();
			
			if (!isCountingDown) {
				countdownThread = new Thread(countdownRunnable);
				countdownThread.start();
			} else {
				timeToExecution = BTN_PRESS_INTERVAL;
			}
						
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		} finally {
			countdownSemaphore.release();
		}
	}
	
	private void stopCountdownToExecution() {
		
		countdownThread.interrupt();
		try {
			countdownThread.join();
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
		
	}
	
}
