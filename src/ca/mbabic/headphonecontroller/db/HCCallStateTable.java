package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

/**
 * Table storing possible call states.
 * @author Marko Babic
 *
 */
public class HCCallStateTable extends HCDbTable {
	
	public HCCallStateTable() {
		TABLE_NAME = "CALLSTATE";
		PRIMARY_KEY_NAME = "key";
		CREATION_STMT = 
			"CREATE TABLE " + TABLE_NAME + "(" 	+
				"key text PRIMARY KEY NOT NULL, " +
				"display_name text NOT NULL" 	+
			");";
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}


	
	
}
