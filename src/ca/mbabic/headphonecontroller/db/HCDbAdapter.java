package ca.mbabic.headphonecontroller.db;

import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.*;

import java.util.ArrayList;
import java.util.Arrays;

import ca.mbabic.headphonecontroller.commands.HCCommand;
import ca.mbabic.headphonecontroller.commands.HCCommandContext;
import ca.mbabic.headphonecontroller.commands.HCCommandFactory;
import ca.mbabic.headphonecontroller.models.HCCmd;
import ca.mbabic.headphonecontroller.models.HCInputSequence;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;
import android.util.Log;

public class HCDbAdapter {

	/**
	 * Reference to database instance to which adapter is connecting.
	 */
	private SQLiteDatabase db;
	
	/**
	 * Reference to HCDbHelper managing database creation.
	 */
	private HCDbHelper dbHelper;
	
	/**
	 * Database file name as stored on device.
	 */
	private static final String dbFilename = "HeadphoneControllerSQLiteDb";
	
	public HCDbAdapter(Context cxt) {
		dbHelper = new HCDbHelper(cxt, dbFilename);
		db = dbHelper.getWritableDatabase();		
	}
	
// Setters ------------------------------------------------------------------- /
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
	public void setStateCommand(String stateKey, String cmdKey, int callState) {
		
	}

// Getters ------------------------------------------------------------------- /
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
	public ArrayList<HCCmd> getCommands(int callState) throws Exception {
		return null;
	}
	
	/**
	 * Get ArrayList of all HCInputSequence objects.
	 */
	public ArrayList<HCInputSequence> getStates() {
		return null;
	}
	
	
	/**
	 * Given a State class, constructs a HCCommandContext object from which
	 * the appropriate command can be invoked.	
	 * TODO: consider changing to "getCommand" which return a command object,
	 * then deferrering the retrieval of the actual command context to the
	 * caller. 
	 * @param state
	 * 		The state for which the HCCommandContext object is to be retrieved.
	 */
	@SuppressWarnings("rawtypes")
	public HCCommandContext getCommandContext(Class state) {

		return null;
	}
	
// Validation functions ------------------------------------------------------ /
	/**
	 * Inspects the validity of the given string as a key for a stored state.
	 * 
	 * @param key
	 *            The state key whose validity is to be established.
	 * @return True if valid, false otherwise.
	 */
	private boolean isValidStateKey(String key) {
		return Arrays.asList(INPUTSEQUENCE_KEYS).contains(key);
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
