package ca.mbabic.headphonecontroller.configuration;

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

	
	private SharedPreferences prefs;
	private TelephonyManager telephonyManager;
	
	/**
	 * 
	 * @param prefs
	 * 		Shared preferences object from which preferences can be retrieved.
	 * @param prefs
	 * 		Application context such that reference to TelephonyManager can
	 * 		be retrieved.
	 */
	public HCConfigAdapter(SharedPreferences sharedPrefs, Context cxt) {
		
		prefs = sharedPrefs;

		telephonyManager = 
			(TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
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
	
}
