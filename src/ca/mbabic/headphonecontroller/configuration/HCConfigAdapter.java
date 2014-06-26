package ca.mbabic.headphonecontroller.configuration;

import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.*;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import android.util.Log;
import ca.mbabic.headphonecontroller.commands.HCCommand;
import ca.mbabic.headphonecontroller.commands.HCCommandContext;
import ca.mbabic.headphonecontroller.commands.HCCommandFactory;
import ca.mbabic.headphonecontroller.models.Command;
import ca.mbabic.headphonecontroller.models.State;

/**
 * Defines methods allowing other classes access to configuration settings.
 * 
 * @author Marko Babic
 * 
 */
public class HCConfigAdapter {

	/**
	 * Class specific logging tag.
	 */
	private static final String TAG = ".configuration.HCConfigAdapter";

	/**
	 * Reference to shared preferences object storing configuration details.
	 */
	private SharedPreferences prefs;

	/**
	 * Reference to telephonyManager instance such that call state (i.e., if the
	 * phone is ringing, if a call is ongoing, etc.) can be queried.
	 */
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

	
	/**
	 * Returns State object associated with the given state key or null if no
	 * such object exists.
	 * @param stateKey
	 * 		The key of the state to be retrieved from configuration store.
	 */
	public State getState(String stateKey) {

		String[] cmds;
		Command idleCmd, offHookCmd, ringingCmd;
		String idleCmdKey, offHookCmdKey, ringingCmdKey, stateName, 
		idleCmdName, offHookCmdName, ringingCmdName, cmdStr;	
		
		if (!isValidStateKey(stateKey)) return null;
		
		cmdStr = prefs.getString(stateKey, "");
		
		cmds = cmdStr.split(COMMAND_DELIMITER);
		
		idleCmdKey 		= cmds[TelephonyManager.CALL_STATE_IDLE];
		offHookCmdKey 	= cmds[TelephonyManager.CALL_STATE_OFFHOOK];
		ringingCmdKey 	= cmds[TelephonyManager.CALL_STATE_RINGING];
		
		idleCmdName 	= HCConfigAdapter.keyToName(idleCmdKey);
		offHookCmdName 	= HCConfigAdapter.keyToName(offHookCmdKey);
		ringingCmdName 	= HCConfigAdapter.keyToName(ringingCmdKey);
		
		idleCmd 		= new Command(idleCmdKey, idleCmdName);
		offHookCmd 		= new Command(offHookCmdKey, offHookCmdName);
		ringingCmd 		= new Command(ringingCmdKey, ringingCmdName);
		
		stateName 		= HCConfigAdapter.keyToName(stateKey);
		
		return new State(stateKey, stateName, idleCmd, offHookCmd, ringingCmd);
	}
	
	/**
	 * Get collection of state objects from configuration file.
	 */
	public ArrayList<State> getStates() {

		ArrayList<State> ret;
		String stateKey;
		int i;

		ret = new ArrayList<State>();		
		
		for (i = 0; i < STATE_KEYS.length; i++) {

			stateKey = STATE_KEYS[i];
			
			ret.add(getState(stateKey));						
		}
		
		return ret;
	}
	
	/**
	 * Get collection of all possible commands from the configuration file
	 * by call state.
	 * 
	 * @param callState
	 * 		The call state for which all possible commands are to be retrieved.
	 * @return
	 * 		ArrayList of command objects.
	 * @throws Exception
	 * 		Throws exception if given invalid call state.
	 */
	public ArrayList<Command> getCommands(int callState) throws Exception {
		
		ArrayList<Command> ret;
		
		if (!isValidCallState(callState)) {
			throw new Exception("Invalid call state value.");
		}
		
		ret = new ArrayList<Command>();
		
		for (String cmdKey : CMD_KEYS) {
			
			// Search for callState in validCallStates -- if it is in there,
			// then we construct a new command object and add it to the array
			// to be returned.  isValidCommandKey() does this work for us.
			if (isValidCommandKey(cmdKey, callState)) {
				
				ret.add(new Command(cmdKey, keyToName(cmdKey)));
				
			}	
		}
		
		return ret;
	}

	/**
	 * Converts the given command or state key to a String representing the name
	 * of that command or state.
	 * 
	 * @param key
	 *  	The state key from which the state name is to be derived.
	 * @return
	 * 		The name of the state specified by the given key.
	 */
	public static String keyToName(String key) {

		String[] splitKey;
		String name, ret = "";
		int i;
		char c;

		splitKey = key.split(".");

		name = splitKey[splitKey.length - 1];

		for (i = 0; i < name.length(); i++) {

			c = name.charAt(i);
			
			if (Character.isUpperCase(c) && i > 0) {
				ret += " ";
			}
			
			ret += c;
		}

		return ret;
	}

	/**
	 * 
	 * Set the command to be executed in the given HCStateMachine state 
	 * in the given call state.
	 * 
	 * Command string stored in format:
	 * CMD_FOR_CALL_IDLE::CMD_FOR_CALL_RINGING::CMD_FOR_CALL_OFFHOOK
	 * 
	 * @param stateKey
	 * 		The key of the HCStateMachine state in which the command is to
	 * 		executed.
	 * @param cmdKey
	 * 		The key of the command to be executed.
	 * @param callState
	 * 		The call state to which the command applies.
	 * @throws Exception
	 *		Throws exception if passed invalid state or command key.
	 */
	public void setStateCommand(String stateKey, String cmdKey, int callState)
			throws Exception {

		String[] cmds;
		String cmdStr, newCmdStr;
		int i;

		// Ensure that given stateKey, cmdKey and callState are valid.
		// TODO: throw better exception type, break into separate cases for more
		// informative exception message.
		if (!isValidStateKey(stateKey) || !isValidCommandKey(cmdKey, callState)) {

			throw new Exception("Invalid state or command key.");

		}

		cmdStr = prefs.getString(stateKey, null);

		if (cmdStr == null) {

			// No preferences thus far set for this command.
			// TODO: can this happen/should this be allowed to happen?

		}

		cmds = cmdStr.split(COMMAND_DELIMITER);

		// Construct the new string to be stored.
		newCmdStr = "";
		for (i = 0; i < N_CALL_STATES; i++) {

			if (i != callState) {
				newCmdStr += cmds[i];
			} else {
				newCmdStr += cmdKey;
			}

			// Put delimiter between the first/second and second/third
			// commands.
			if (i < N_CALL_STATES - 1) {
				newCmdStr += COMMAND_DELIMITER;
			}
		}

		// Store and commit changes to shared preferences object.
		prefs.edit().putString(stateKey, newCmdStr).commit();

	}

	/**
	 * Given a State class, constructs a HCCommandContext object from which
	 * the appropriate command can be invoked.	 * 
	 * TODO: should this really be the responsibility of HCConfigAdapter?
	 * Consider refactoring so that HCConfigAdapter class does not need 
	 * reference to TelephonyManager to view state.
	 * @param state
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public HCCommandContext getCommandContext(Class state) {

		HCCommand cmd;
		String[] cmds;
		String storedCmdStr, cmdStr;

		Log.d(TAG, "getCommandContext called with state = " + state.getName());

		storedCmdStr = prefs.getString(state.getName(), null);

		Log.d(TAG, "storedCmdStr = " + storedCmdStr);

		cmds = storedCmdStr.split(COMMAND_DELIMITER);

		Log.d(TAG, "cmds[0], cmds[1], cmds[2] = " + cmds[0] + ", " + cmds[1]
				+ ", " + cmds[2]);

		cmdStr = cmds[telephonyManager.getCallState()];

		Log.d(TAG, "cmdStr = " + cmdStr);

		try {

			cmd = HCCommandFactory.createInstance(cmdStr);

			return new HCCommandContext(cmd);

		} catch (Exception e) {

			Log.e(TAG, e.getMessage());

		}

		// Got here only if exception was thrown, return null and let
		// program crash as this implies fatal error anyway.
		return null;

	}

	/**
	 * Writes the default configuration values to the SharedPreferences object.
	 */
	public void writeDefaultConfigurationValues() {

		Editor edit;
		String cmdStr;

		edit = prefs.edit();

		// Write default values for OnePressState.
		cmdStr = PLAYPAUSE_CMD_KEY + COMMAND_DELIMITER + NO_OP_CMD_KEY
				+ COMMAND_DELIMITER + NO_OP_CMD_KEY;
		edit.putString(ONE_PRESS_STATE_KEY, cmdStr).commit();

		// Write default values for TwoPressState.
		cmdStr = SKIP_CMD_KEY + COMMAND_DELIMITER + NO_OP_CMD_KEY
				+ COMMAND_DELIMITER + NO_OP_CMD_KEY;
		edit.putString(TWO_PRESS_STATE_KEY, cmdStr).commit();

		// Write default values for ThreePressState.
		cmdStr = PREVIOUS_CMD_KEY + COMMAND_DELIMITER + NO_OP_CMD_KEY
				+ COMMAND_DELIMITER + NO_OP_CMD_KEY;
		edit.putString(THREE_PRESS_STATE_KEY, cmdStr).commit();

		// Write default values for FourPressState.
		cmdStr = MUTE_MUSIC_CMD_KEY + COMMAND_DELIMITER + NO_OP_CMD_KEY
				+ COMMAND_DELIMITER + NO_OP_CMD_KEY;
		edit.putString(FOUR_PRESS_STATE_KEY, cmdStr).commit();

	}

	/**
	 * Retrieve the value of the "hasRunBefore" key from the shared preferences
	 * object.
	 */
	public boolean hasApplicationRunBefore() {
		return prefs.getBoolean(HAS_RUN_BEFORE_KEY, false);
	}

	/**
	 * Set the value of the "hasRunBefore" key in the shared preferences object
	 * to be true.
	 */
	public void setHasRunBefore() {
		prefs.edit().putBoolean(HAS_RUN_BEFORE_KEY, true);
	}

	// Validation functions
	/**
	 * Inspects the validity of the given string as a key for a stored state.
	 * 
	 * @param key
	 *            The state key whose validity is to be established.
	 * @return True if valid, false otherwise.
	 */
	private boolean isValidStateKey(String key) {
		return Arrays.asList(STATE_KEYS).contains(key);
	}

	/**
	 * Inspects the validity of the given string as a key for a stored state.
	 * 
	 * @param key
	 *            Command string to be validated.
	 * 
	 * @param callState
	 *            The call state associated with the command.
	 * 
	 * @return True if valid, false otherwise.
	 */
	private boolean isValidCommandKey(String key, int callState) {

		// Ensure key is valid.
		if (Arrays.binarySearch(CMD_KEYS, key) == -1)
			return false;

		// Ensure that command can be invoked in the specified call state.
		Arrays.sort(VALID_CMD_STATES.get(key));
		if (Arrays.binarySearch(VALID_CMD_STATES.get(key), callState) == -1)
			return false;

		return true;
	}
	
	/**
	 * Determines if the given callState is of a valid value.
	 * @param callState
	 * 		The call state whose validity is to be determined.
	 * @return
	 * 		True if the call state is valid, false otherwise.
	 */
	private boolean isValidCallState(int callState) {
		if 	(	
				callState != TelephonyManager.CALL_STATE_IDLE 		&&
				callState != TelephonyManager.CALL_STATE_OFFHOOK	&&
				callState != TelephonyManager.CALL_STATE_RINGING
		) {
			return false;
		}
		
		return true;
	}

}
