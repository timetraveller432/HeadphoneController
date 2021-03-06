package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Call extending SQLiteOpenHelper implementing database creation and
 * table creation statements.
 * @author Marko Babic
 */
public class HCDbHelper extends SQLiteOpenHelper {

	/**
	 * Db version number.
	 */
	private static final int DB_VERSION = 1;
	
	public HCDbHelper(Context context, String name) {
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

		HCDbTable table;
		
		// Create CallState table and insert default values.
		table = new HCCallStateTable();
		table.onCreate(db);
		
		for (ContentValues cv : table.getDefaultValues()) {
			
			db.insert(table.getTableName(), null, cv);
			
		}
		
		// Create Command table.
		table = new HCCommandTable();
		table.onCreate(db);
		
		for (ContentValues cv : table.getDefaultValues()) {
			
			db.insert(table.getTableName(), null, cv);
			
		}
		
		// Create InputSequence table.
		table = new HCInputSequenceTable();
		table.onCreate(db);
		
		for (ContentValues cv : table.getDefaultValues()) {
			
			db.insert(table.getTableName(), null, cv);
			
		}
		
		// Create table encoding which commands can be called from which
		// call states.
		table = new HCCommandCallStateTable();
		table.onCreate(db);
		
		for (ContentValues cv : table.getDefaultValues()) {
			
			db.insert(table.getTableName(), null, cv);
			
		}
		
		
		// Create table encoding which commands are set for each input sequence
		// given a call state.
		table = new HCInputSequenceCommandsTable();
		table.onCreate(db);
		
		for (ContentValues cv : table.getDefaultValues()) {
			
			db.insert(table.getTableName(), null, cv);
			
		}

	}

}
