package ca.mbabic.headphonecontroller.configuration;

import java.util.HashMap;

import android.telephony.TelephonyManager;
import ca.mbabic.headphonecontroller.commands.PlayPauseCommand;
import ca.mbabic.headphonecontroller.commands.PreviousCommand;
import ca.mbabic.headphonecontroller.commands.SkipCommand;
import ca.mbabic.headphonecontroller.statemachine.OnePressState;
import ca.mbabic.headphonecontroller.statemachine.ThreePressState;
import ca.mbabic.headphonecontroller.statemachine.TwoPressState;

public class HCConfigConstants {

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

	public static final String COMMAND_DELIMITER = "|";

	/**
	 * {value #NO_OP_CMD_KEY} Storage value for no-op command.
	 */
	public static final String NO_OP_CMD_KEY = "NO-OP";

	/**
	 * Storage value for the play/pause command.
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
	 * Number of possible call states.
	 */
	public static final int N_CALL_STATES = 3;

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
}
