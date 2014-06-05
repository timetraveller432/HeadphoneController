/*
 * TODO: license
 */

package ca.mbabic.headphonecontroller.statemachine;

import java.lang.Thread.State;
import java.util.concurrent.Semaphore;

import android.util.Log;
import ca.mbabic.headphonecontroller.services.MediaButtonListenerService;

/**
 * State machine keeping track of the state of the button presses.
 * Implementation of the State Pattern. See:
 * http://sourcemaking.com/design_patterns/state
 * 
 * @author Marko Babic
 */
public class HCStateMachine {

	/**
	 * Reference to MediaButtonListenerService such that after command execution
	 * we can re-register ourselves as exclusive media button player listener.
	 */
	private static MediaButtonListenerService listenerService;
	
	/**
	 * Class specific logging tag.
	 */
	private static final String TAG = ".statemachine.HCStateMachine";

	/**
	 * Interval to wait between button presses.
	 */
	// TODO: make configurable
	private static final long BTN_PRESS_INTERVAL = 400;

	/**
	 * Instance of HCStateMachine available to the application.
	 */
	private static volatile HCStateMachine instance = null;

	/**
	 * Reference to the state in which the machine is currently in.
	 */
	private static HCState currentState;

	/**
	 * The starting state of the machine.
	 */
	private static HCState startState;

	/**
	 * Time left until count down thread will execute the current state's
	 * command.
	 */
	private static long timeToExecution;

	/**
	 * The amount of time the count down thread will sleep between acquiring the
	 * semaphore and updating timeToExecution.
	 */
	private static final long SLEEP_INTERVAL = 100;

	/**
	 * Semaphore used to lock access to shared variable timeToExecution.
	 */
	private static Semaphore countDownSemaphore = new Semaphore(1);

	/**
	 * Thread in which the count down to execution monitoring functionality is
	 * implemented.
	 */
	private Thread countDownThread;

	static Runnable countDownRunnable = new Runnable() {

		public void run() {

			timeToExecution = BTN_PRESS_INTERVAL;

			while (timeToExecution > 0) {

				try {
					Thread.sleep(SLEEP_INTERVAL);
					countDownSemaphore.acquire();
					timeToExecution -= SLEEP_INTERVAL;
				} catch (InterruptedException ie) {
					Log.e(TAG, "Coundown thread interrupted!");
					return;
				} finally {
					countDownSemaphore.release();
				}

			}

			// Count down expired, execute current state's command.
			// TODO: this should be synchronized ... and we should take one
			// last check here to make sure timeToExecution <= 0 and if not
			// start looping again and not execute anything ...
			currentState.executeCommand();
			listenerService.registerAsMediaButtonListener();
			currentState = startState;
		}

	};

	/**
	 * Private constructor. Get global application reference to state machine
	 * via getInstance().
	 */
	private HCStateMachine() {
		startState = new InactiveState();
		currentState = startState;
	}

	public void setService(MediaButtonListenerService mbls) {
		listenerService = mbls;
	}
	
	/**
	 * @return Singleton instance of HCStateMachine.
	 */
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
	 * Indicate to state machine that the media button has been pressed on the
	 * headphones. TODO: keep track of long clicks... should not transition to
	 * next state at end of such a command.
	 */
	public void keyUp() {
		makeStateTransition();
	}

	/**
	 * Transition to next state as appropriate given currentState.
	 */
	private void makeStateTransition() {

		HCState nextState = currentState.getNextState();

		if (currentState.isTerminal()) {
			stopCountDownToExecution();
			currentState.executeCommand();
			currentState = startState;
		} else {
			currentState = nextState;
			startCountDownToExecution();
		}
	}

	/**
	 * Call to start or reset a count down to execution of the current state's
	 * command.
	 */
	private void startCountDownToExecution() {

		try {

			countDownSemaphore.acquire();

			if (countDownThread == null
					|| countDownThread.getState() == State.TERMINATED) {

				countDownThread = new Thread(countDownRunnable);
				countDownThread.start();
			} else {
				timeToExecution = BTN_PRESS_INTERVAL;
			}

		} catch (Exception e) {
			Log.e(TAG, e.toString());
		} finally {
			countDownSemaphore.release();
		}
	}

	/**
	 * Called to cancel count down to execution thread.
	 */
	private void stopCountDownToExecution() {

		countDownThread.interrupt();
		try {
			countDownThread.join();
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}

	}

}
