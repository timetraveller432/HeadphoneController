package ca.mbabic.headphonecontroller.configuration;

import java.util.Arrays;
import java.util.HashMap;

import android.telephony.TelephonyManager;
import ca.mbabic.headphonecontroller.commands.MuteMusicCommand;
import ca.mbabic.headphonecontroller.commands.NoOpCommand;
import ca.mbabic.headphonecontroller.commands.PlayPauseCommand;
import ca.mbabic.headphonecontroller.commands.PreviousCommand;
import ca.mbabic.headphonecontroller.commands.SkipCommand;
import ca.mbabic.headphonecontroller.statemachine.FourPressState;
import ca.mbabic.headphonecontroller.statemachine.OnePressState;
import ca.mbabic.headphonecontroller.statemachine.ThreePressState;
import ca.mbabic.headphonecontroller.statemachine.TwoPressState;

public class HCConfigConstants {

	/**
	 * Storage key for boolean value indicating whether the application has run
	 * before or not.
	 */
	public static final String HAS_RUN_BEFORE_KEY = 
			"ca.mbabic.headphonecontroller.configuration.HAS_RUN_BEFORE";

	/**
	 * Storage key for the OnePress state.
	 */
	public static final String ONE_PRESS_KEY = OnePressState.class
			.getName();

	
	/**
	 * Storage key for the TwoPress state.
	 */
	public static final String TWO_PRESS_KEY = TwoPressState.class
			.getName();
	/**
	 * Storage key for the ThreePress state.
	 */
	public static final String THREE_PRESS_KEY = ThreePressState.class
			.getName();

	/**
	 * Storage key for the FourPress state.
	 */
	public static final String FOUR_PRESS_KEY = FourPressState.class
			.getName();

	/**
	 * Array of valid state storage keys.
	 */
	public static final String[] INPUTSEQUENCE_KEYS = new String[] {

	ONE_PRESS_KEY, TWO_PRESS_KEY, THREE_PRESS_KEY,
			FOUR_PRESS_KEY

	};

	public static final String COMMAND_DELIMITER = "::";

	/**
	 * {value #NO_OP_CMD_KEY} Storage value for no-op command.
	 */
	public static final String NO_OP_CMD_KEY = NoOpCommand.class.getName();

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
	 * Storage value for the mute song command.
	 */
	public static final String MUTE_MUSIC_CMD_KEY = MuteMusicCommand.class
			.getName();

	/**
	 * Array of valid storage values for commands.  Keys are sorted such that
	 * a binary serach can be performed on the array to search for a value.
	 */
	public static final String[] CMD_KEYS = new String[] {

	PLAYPAUSE_CMD_KEY, SKIP_CMD_KEY, PREVIOUS_CMD_KEY, MUTE_MUSIC_CMD_KEY

	};
	// Sort CMD_KEYS to allows for binary searches.
	static {
		Arrays.sort(CMD_KEYS);
	}

	/**
	 * Number of possible call states.
	 */
	public static final int N_CALL_STATES = 3;
	
	public static final int[] CALL_STATE_KEYS = new int[] {
		
		TelephonyManager.CALL_STATE_IDLE, TelephonyManager.CALL_STATE_OFFHOOK,
		TelephonyManager.CALL_STATE_RINGING
		
	};

	/**
	 * Map from command storage values to the call states in which the given
	 * command can be appropriately invoked.
	 */
	public static final HashMap<String, int[]> VALID_CMD_STATES;
	static {

		VALID_CMD_STATES = new HashMap<String, int[]>();

		// No-op command.
		VALID_CMD_STATES.put(NO_OP_CMD_KEY, new int[] {
				TelephonyManager.CALL_STATE_IDLE,
				TelephonyManager.CALL_STATE_OFFHOOK,
				TelephonyManager.CALL_STATE_RINGING });

		// Play/pause command.
		VALID_CMD_STATES.put(PLAYPAUSE_CMD_KEY,
				new int[] { TelephonyManager.CALL_STATE_IDLE });

		// Skip song command.
		VALID_CMD_STATES.put(SKIP_CMD_KEY,
				new int[] { TelephonyManager.CALL_STATE_IDLE });

		// Repeat/Previous song command.
		VALID_CMD_STATES.put(PREVIOUS_CMD_KEY,
				new int[] { TelephonyManager.CALL_STATE_IDLE });
		
		// Mute music command.
		VALID_CMD_STATES.put(MUTE_MUSIC_CMD_KEY, 
				new int[] { TelephonyManager.CALL_STATE_IDLE });

		// TODO: answer phone command

		// TODO: hang up command

		// TODO: hold/unhold command

		// ...
	}
	
	/**
	 * Display name for command.  Map is from command key => command display 
	 * string.
	 */
	public static final HashMap<String, String> CMD_DISPLAY_NAMES;
	static {
		
		CMD_DISPLAY_NAMES = new HashMap<String, String>();
		
		// No-op command.
		CMD_DISPLAY_NAMES.put(NO_OP_CMD_KEY, "Perform no action");
		
		// Play/Pause command.
		CMD_DISPLAY_NAMES.put(PLAYPAUSE_CMD_KEY, "Play/Pause");
		
		// Skip song command.
		CMD_DISPLAY_NAMES.put(SKIP_CMD_KEY, "Skip track");
		
		// Repeat/Previous song command.
		CMD_DISPLAY_NAMES.put(PREVIOUS_CMD_KEY, "Repeat/Go to previous track");
		
		// Mute music command.
		CMD_DISPLAY_NAMES.put(MUTE_MUSIC_CMD_KEY, "Mute");
		
		
	}
	
	/**
	 * Display names for input sequences.  Map is from input sequence key =>
	 * input sequence display string.
	 */
	public static final HashMap<String, String> INPUTSEQUENCE_DISPLAY_NAMES;
	static {
		
		INPUTSEQUENCE_DISPLAY_NAMES = new HashMap<String, String>();
		
		// One press.
		INPUTSEQUENCE_DISPLAY_NAMES.put(ONE_PRESS_KEY, "Single click.");
		
		// Two press.
		INPUTSEQUENCE_DISPLAY_NAMES.put(TWO_PRESS_KEY, "Double click.");
		
		// Three press.
		
		INPUTSEQUENCE_DISPLAY_NAMES.put(THREE_PRESS_KEY, "Triple click.");
		
		// Four press.
		INPUTSEQUENCE_DISPLAY_NAMES.put(FOUR_PRESS_KEY, "Quadruple click.");
		
	}
	
	/**
	 * Display names for call states.  Map is from call state id => call state
	 * display string.
	 */
	public static final HashMap<Integer, String> CALLSTATES_DISPLAY_NAMES;
	static {
		
		CALLSTATES_DISPLAY_NAMES = new HashMap<Integer, String>();
		
		CALLSTATES_DISPLAY_NAMES.put(TelephonyManager.CALL_STATE_IDLE, 
				"No call is active.");
		
		CALLSTATES_DISPLAY_NAMES.put(TelephonyManager.CALL_STATE_OFFHOOK, 
				"A call is in progress.");
		
		CALLSTATES_DISPLAY_NAMES.put(TelephonyManager.CALL_STATE_RINGING, 
				"The phone is ringing.");
		
	}
	
	public static final HashMap<String, String[]> DEFAULT_CONFIGURATION;
	static {
		
		String[] cmdsByState;
				
		DEFAULT_CONFIGURATION = new HashMap<String, String[]>();
		
		// One press default configuration.
		cmdsByState	= new String[N_CALL_STATES];

		cmdsByState[TelephonyManager.CALL_STATE_IDLE % N_CALL_STATES] =
			PLAYPAUSE_CMD_KEY;
		
		cmdsByState[TelephonyManager.CALL_STATE_OFFHOOK % N_CALL_STATES] =
				NO_OP_CMD_KEY;
		
		cmdsByState[TelephonyManager.CALL_STATE_RINGING % N_CALL_STATES] =
				NO_OP_CMD_KEY;
		
		DEFAULT_CONFIGURATION.put(ONE_PRESS_KEY, cmdsByState);
		
		// Two press default configuration
		cmdsByState	= new String[N_CALL_STATES];

		cmdsByState[TelephonyManager.CALL_STATE_IDLE % N_CALL_STATES] =
			SKIP_CMD_KEY;
		
		cmdsByState[TelephonyManager.CALL_STATE_OFFHOOK % N_CALL_STATES] =
				NO_OP_CMD_KEY;
		
		cmdsByState[TelephonyManager.CALL_STATE_RINGING % N_CALL_STATES] =
				NO_OP_CMD_KEY;
		
		DEFAULT_CONFIGURATION.put(TWO_PRESS_KEY, cmdsByState);
		
		// Three press default configuration.
		cmdsByState	= new String[N_CALL_STATES];

		cmdsByState[TelephonyManager.CALL_STATE_IDLE % N_CALL_STATES] =
			PREVIOUS_CMD_KEY;
		
		cmdsByState[TelephonyManager.CALL_STATE_OFFHOOK % N_CALL_STATES] =
				NO_OP_CMD_KEY;
		
		cmdsByState[TelephonyManager.CALL_STATE_RINGING % N_CALL_STATES] =
				NO_OP_CMD_KEY;
		
		DEFAULT_CONFIGURATION.put(THREE_PRESS_KEY, cmdsByState);
		
		// Four press default configuration.
		cmdsByState	= new String[N_CALL_STATES];

		cmdsByState[TelephonyManager.CALL_STATE_IDLE % N_CALL_STATES] =
			MUTE_MUSIC_CMD_KEY;
		
		cmdsByState[TelephonyManager.CALL_STATE_OFFHOOK % N_CALL_STATES] =
				NO_OP_CMD_KEY;
		
		cmdsByState[TelephonyManager.CALL_STATE_RINGING % N_CALL_STATES] =
				NO_OP_CMD_KEY;
		
		DEFAULT_CONFIGURATION.put(FOUR_PRESS_KEY, cmdsByState);
		
	}
	
	
}
