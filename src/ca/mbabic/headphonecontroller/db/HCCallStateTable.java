package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.*;

/**
 * Table storing possible call states.
 * @author Marko Babic
 *
 */


public class HCCallStateTable extends HCDbTable {
	
	public static final String DISPLAY_NAME_KEY = "display_name";
	
	public HCCallStateTable() {
		TABLE_NAME = "CALLSTATE";
		PRIMARY_KEY_NAME = "id";
		CREATION_STMT = 
			"CREATE TABLE " + TABLE_NAME + "(" 	+
				PRIMARY_KEY_NAME + " int PRIMARY KEY NOT NULL, " +
				DISPLAY_NAME_KEY + " text NOT NULL" 	+
			");";
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {

		ArrayList<ContentValues> ret;
		ContentValues cv;
		
		ret = new ArrayList<ContentValues>();
		
		for (int key : CALL_STATE_KEYS) {
			
			cv = new ContentValues();			
			cv.put(PRIMARY_KEY_NAME, key);
			cv.put(DISPLAY_NAME_KEY, CALLSTATES_DISPLAY_NAMES.get(key));
		
			ret.add(cv);
			
		}
	
		return ret;
		
	}


	
	
}
