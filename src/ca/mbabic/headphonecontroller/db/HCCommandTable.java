package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class HCCommandTable extends HCDbTable {

	private static final String TABLE_NAME = "COMMAND";
	
	private static final String PRIMARY_KEY_NAME = "id";
	
	private static final String CREATION_STMT = 
			"CREATE TABLE COMMAND(" 			+
				"id int PRIMARY KEY NOT NULL, " +
				"key text NOT NULL, "			+
				"name text NOT NULL"			+
			");";
		
	
	public HCCommandTable() {
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		// TODO: implement.
		return null;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getCreationStatement() {
		return CREATION_STMT;
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return PRIMARY_KEY_NAME;
	}

	
	
}
