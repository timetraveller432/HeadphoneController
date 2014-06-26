package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class HCCommandTable extends HCDbTable {		
	
	public HCCommandTable() {
		
		TABLE_NAME = "COMMAND";
		PRIMARY_KEY_NAME = "id";
		CREATION_STMT =
			"CREATE TABLE COMMAND(" 			+
				"id int PRIMARY KEY NOT NULL, " +
				"key text NOT NULL, "			+
				"name text NOT NULL"			+
			");";
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}

}
