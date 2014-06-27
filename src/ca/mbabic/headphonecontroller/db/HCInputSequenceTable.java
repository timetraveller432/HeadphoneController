package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

public class HCInputSequenceTable extends HCDbTable {
	
	public HCInputSequenceTable() {
		
		TABLE_NAME = "INPUTSEQUENCE";
		
		PRIMARY_KEY_NAME = "key";
		
		CREATION_STMT = 			
			"CREATE TABLE " + TABLE_NAME + "(" 		+
				"key text PRIMARY KEY NOT NULL, " 	+
				"name text NOT NULL" 				+
			");";
	
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}
}
