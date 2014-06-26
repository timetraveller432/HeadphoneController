package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Interface defined 
 * @author Marko
 */
public abstract class HCDbTable {
	
	
	protected String TABLE_NAME;
	protected String CREATION_STMT;
	protected String PRIMARY_KEY_NAME;
	
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(getCreationStatement());
	}
		
	/**
	 * @return 
	 * 		ArrayList of ContentValues objects -- each object encoding
	 * 		the default values 
	 */	
	public abstract ArrayList<ContentValues> getDefaultValues();
	/**
	 * @return
	 * 		The name of the table.
	 */
	public String getTableName() {
		return TABLE_NAME;
	}

	/**
	 * @return
	 * 		The SQL statement which creates the table.
	 */
	public String getCreationStatement() {
		return CREATION_STMT;
	}

	/**
	 * @return
	 * 		The name of the column which serves as the primary key for the 
	 * 		table.
	 */
	public String getPrimaryKeyColumnName() {
		return PRIMARY_KEY_NAME;
	}
}
