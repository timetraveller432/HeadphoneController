package ca.mbabic.headphonecontroller.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	/**
	 * Db version number.
	 */
	private static final int DB_VERSION = 1;
	
	/**
	 * Command table execution statement.
	 */
	private static final String createCommandTableStmt = 
			"CREATE TABLE COMMAND(" 			+
				"id int PRIMARY KEY NOT NULL," 	+
				"key text NOT NULL,"			+
				"name text NOT NULL,"			+
			");";
	
	private static final String createCallStateTableStmt =
			"CREATE TABLE CALLSTATE(" 			+
				"id int PRIMARY KEY NOT NULL" 	+
				"name text NOT NULL" 			+
			");";
	
	private static final String createCommandCallStatesTableStmt =
			"CREATE TABLE COMMAND_CALLSTATES("  					+
				"command_id int," 									+
				"FOREIGN KEY (command_id) REFERENCES COMMAND(id)," 	+
				"FOREIGN KEY (callstate_id) REFERENCE CALLSTATE(id)"+
			");";
	
	
	public DbHelper(Context context, String name) {
		super(context, name, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		executeCreationStatements(db);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	private void executeCreationStatements(SQLiteDatabase db) {
		
		db.execSQL(createCommandTableStmt);
		db.execSQL(createCallStateTableStmt);
		db.execSQL(createCommandCallStatesTableStmt);
		
	}

}
