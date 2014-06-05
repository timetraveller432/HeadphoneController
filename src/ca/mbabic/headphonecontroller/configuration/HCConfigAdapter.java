package ca.mbabic.headphonecontroller.configuration;

import java.util.Arrays;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import ca.mbabic.headphonecontroller.commands.HCCommandContext;
import ca.mbabic.headphonecontroller.commands.PlayPauseCommand;
import ca.mbabic.headphonecontroller.commands.PreviousCommand;
import ca.mbabic.headphonecontroller.commands.SkipCommand;
import ca.mbabic.headphonecontroller.statemachine.OnePressState;
import ca.mbabic.headphonecontroller.statemachine.ThreePressState;
import ca.mbabic.headphonecontroller.statemachine.TwoPressState;

/**
 * Defines methods allowing other classes access to configuration settings.
 * 
 * @author Marko Babic
 * 
 */
public class HCConfigAdapter {

	/**
	 * Storage key for the OnePress state.
	 */
	public static final String ONE_PRESS_STATE_KEY = OnePressState.class
			.getName();
	
	/**
	 * Storage key for the TwoPress state.
	 */
	public static final String TWO_PRESS_STATE_KEY = TwoPressState.class
			.getName();
	/**
	 * Storage key for the ThreePress state.
	 */
	public static final String THREE_PRESS_STATE_KEY = ThreePressState.class
			.getName();

	/**
	 * Array of valid state storage keys.
	 */
	public static final String[] STATE_KEYS = new String[] {

	ONE_PRESS_STATE_KEY, TWO_PRESS_STATE_KEY, THREE_PRESS_STATE_KEY

	};

	/**
	 * Storage value for the play/puase command.
	 */
	public static final String PLAYPAUSE_CMD_KEY = PlayPauseCommand.class
			.getName();
	
	/**
	 * Storage value for the skip song command.
	 */
	public static final String SKIP_CMD_KEY = SkipCommand.class.getName();
	
	/**
	 * Storage value for the repeat/previous song command.
	 */
	public static final String PREVIOUS_CMD_KEY = PreviousCommand.class
			.getName();

	/**
	 * Array of valid storage values for commands.
	 */
	public static final String[] CMD_KEYS = new String[] {

	PLAYPAUSE_CMD_KEY, SKIP_CMD_KEY, PREVIOUS_CMD_KEY

	};

	/**
	 * Map from command storage values to the call states in which the given
	 * command can be appropriately invoked.
	 */
	public static final HashMap<String, int[]> VALID_CMD_STATES;
	static {

		VALID_CMD_STATES = new HashMap<String, int[]>();

		// Play/pause command.
		VALID_CMD_STATES.put(PLAYPAUSE_CMD_KEY,
				new int[] { TelephonyManager.CALL_STATE_IDLE });

		// Skip song command.
		VALID_CMD_STATES.put(SKIP_CMD_KEY,
				new int[] { TelephonyManager.CALL_STATE_IDLE });

		// Repeat/Previous song command.
		VALID_CMD_STATES.put(PREVIOUS_CMD_KEY,
				new int[] { TelephonyManager.CALL_STATE_IDLE });
	}

	private SharedPreferences prefs;
	private TelephonyManager telephonyManager;

	/**
	 * @param prefs
	 *            Shared preferences object from which preferences can be
	 *            retrieved.
	 * @param prefs
	 *            Application context such that reference to TelephonyManager
	 *            can be retrieved.
	 */
	public HCConfigAdapter(SharedPreferences sharedPrefs, Context cxt) {

		prefs = sharedPrefs;

		telephonyManager = (TelephonyManager) cxt
				.getSystemService(Context.TELEPHONY_SERVICE);
	}

	public void setStateCommand(String stateKey, String cmdKey, int callState) {

		String cmds;

		// Ensure that given stateKey, cmdKey and callState are valid.
		isValidStateKey(stateKey);

		cmds = prefs.getString(stateKey, null);

		if (cmds == null) {

			//

		}

	}

	public HCCommandContext getCommandContext(Class state) {

		switch (telephonyManager.getCallState()) {

		case TelephonyManager.CALL_STATE_IDLE:
			return getCallIdleCommand(state);

		case TelephonyManager.CALL_STATE_RINGING:

		case TelephonyManager.CALL_STATE_OFFHOOK:
			// At least one call exists that is dialing, active, or on hold,
			// and no calls are ringing or waiting.

			// Fall through for now.

		}

		// TODO: return no-op
		// Below will cause application crash (for now).
		return null;

	}

	private HCCommandContext getCallIdleCommand(Class state) {

		return null;
	}

	// Validation functions

	/**
	 * 
	 * @param key
	 * @return
	 */
	private boolean isValidStateKey(String key) {
		return Arrays.asList(STATE_KEYS).contains(key);
	}

	private boolean isValidCommandKey(String key) {
		return Arrays.asList(CMD_KEYS).contains(key);
	}

}
