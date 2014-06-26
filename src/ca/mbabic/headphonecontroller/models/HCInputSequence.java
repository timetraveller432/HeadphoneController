package ca.mbabic.headphonecontroller.models;

import android.telephony.TelephonyManager;

/**
 * Model representing a state to be used by the views.
 * @author Marko Babic
 * 
 */
public class HCInputSequence {

	private String id;
	
	private String name;

	private HCCmd idleCmd, offHookCmd, ringingCmd;

	public HCInputSequence(String stateKey, String stateName, HCCmd idleCmd,
			HCCmd offHookCmd, HCCmd callActiveCmd) {

		id = stateKey;
		name = stateName;

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCmdStr(int callState) {

		switch (callState) {

		case TelephonyManager.CALL_STATE_IDLE:
			return idleCmd.getName();

		case TelephonyManager.CALL_STATE_OFFHOOK:
			return offHookCmd.getName();

		case TelephonyManager.CALL_STATE_RINGING:
			return ringingCmd.getName();

		}

		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
