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
		PRIMARY_KEY_NAME = "id";
		CREATION_STMT = 
			"CREATE TABLE " + TABLE_NAME + "(" 	+
				"id int PRIMARY KEY NOT NULL, " +
				"name text NOT NULL" 			+
			");";
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}


	
	
}
