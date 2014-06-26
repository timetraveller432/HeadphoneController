package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

public class HCInputSequenceTable extends HCDbTable {
	
	public HCInputSequenceTable() {
		
		TABLE_NAME = "INPUTSEQUENCE";
		
		PRIMARY_KEY_NAME = "id";
		
		CREATION_STMT = 			
			"CREATE TABLE " + TABLE_NAME + "(" 		+
				"id int PRIMARY KEY NOT NULL, " 	+
				"key text NOT NULL, " 				+
				"name text NOT NULL" 				+
			");";
	
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}
}