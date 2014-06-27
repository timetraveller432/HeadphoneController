package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class HCCommandTable extends HCDbTable {		
	
	public HCCommandTable() {
		
		TABLE_NAME = "COMMAND";
		PRIMARY_KEY_NAME = "key";
		CREATION_STMT =
			"CREATE TABLE " + TABLE_NAME + "(" 	+
				"key text PRIMARY KEY NOT NULL, " +
				"name text NOT NULL"			+
			");";
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {

		ArrayList<ContentValues> ret;
		ContentValues cv;
		
		// 
		cv = new ContentValues();
		
		return null;
	}

}
