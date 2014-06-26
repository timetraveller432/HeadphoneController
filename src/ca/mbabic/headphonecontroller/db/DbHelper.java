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
	 * COMMAND table execution statement.
	 */
	private static final String createCommandTableStmt = 
			"CREATE TABLE COMMAND(" 			+
				"id int PRIMARY KEY NOT NULL, " 	+
				"key text NOT NULL, "			+
				"name text NOT NULL"			+
			");";
	
	/**
	 * CALLSTATE table creation statement.
	 */
	private static final String createCallStateTableStmt =
			"CREATE TABLE CALLSTATE(" 			+
				"id int PRIMARY KEY NOT NULL, " +
				"name text NOT NULL" 			+
			");";
	
	/**
	 * COMMAND_CALLSTATES table -- the table encoding the call states
	 * in which a command can be executed -- creation statement.
	 */
	private static final String createCommandCallStatesTableStmt =
			"CREATE TABLE COMMAND_CALLSTATES("  					+
				"command_id int, " 									+
				"callstate_id int, " 								+
				"FOREIGN KEY (command_id) REFERENCES COMMAND(id), "	+
				"FOREIGN KEY (callstate_id) REFERENCE CALLSTATE(id)"+
			");";
	
	/**
	 * INPUTSEQUENCE table creation statement.
	 */
	private static final String createInputSequenceTableStmt = 
			"CREATE TABLE INPUTSEQUENCE(" 		+
				"id int PRIMARY KEY NOT NULL, " 	+
				"key text NOT NULL, " 			+
				"name text NOT NULL" 			+
			");";
	
	/**
	 * INPUTSEQUENCE_COMMANDS table -- the table encoding the commands
	 * to be executed for the given input sequence in a particular call
	 * state -- creation statement.
	 */
	private static final String createISCommandsTableStmt = 
			"CREATE TABLE INPUTSEQUENCE_COMMANDS(" 	+
				"inputsequence_id int, " 			+
				"command_id int, " 					+
				"callstate_id int, " 				+
				"FOREIGN KEY (inputsequence_id) REFERENCES INPUTSEQUENCE(id), " +
				"FOREIGN KEY (command_id) REFERENCES COMMAND(id), " +
				"FOREIGN KEY (callstate_id) REFERENCES CALLSTATE(id)" +
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
	
	/**
	 * Executes table creation statement.
	 * @param db
	 * 		The database instance on which to execute the commands.
	 */
	private void executeCreationStatements(SQLiteDatabase db) {
		
		db.execSQL(createCommandTableStmt);
		db.execSQL(createCallStateTableStmt);
		db.execSQL(createCommandCallStatesTableStmt);
		db.execSQL(createInputSequenceTableStmt);
		db.execSQL(createISCommandsTableStmt);
	}

}
