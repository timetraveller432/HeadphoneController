package ca.mbabic.headphonecontroller.configuration;

import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.CMD_KEYS;
import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.COMMAND_DELIMITER;
import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.N_CALL_STATES;
import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.STATE_KEYS;
import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.VALID_CMD_STATES;

import java.util.Arrays;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import ca.mbabic.headphonecontroller.commands.HCCommandContext;

/**
 * Defines methods allowing other classes access to configuration settings.
 * 
 * @author Marko Babic
 * 
 */
public class HCConfigAdapter {


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

			if (i < N_CALL_STATES - 1) {
				newCmdStr += COMMAND_DELIMITER;
			}
		}
		
		// Store and commit changes to shared preferences.
		prefs.edit().putString(stateKey, newCmdStr).commit();

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

	private boolean isValidCommandKey(String key, int callState) {

		// Ensure key is valid.
		if (!Arrays.asList(CMD_KEYS).contains(key))
			return false;

		// Ensure that command can be invoked in the specified call state.
		if (!Arrays.asList(VALID_CMD_STATES.get(key)).contains(callState))
			return false;

		return true;
	}

}
