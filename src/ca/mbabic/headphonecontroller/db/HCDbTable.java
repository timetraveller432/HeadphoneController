package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Interface defined 
 * @author Marko
 */
public abstract class HCDbTable {
	
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
	public abstract String getTableName();
	
	/**
	 * @return
	 * 		The SQL statement which creates the table.
	 */
	public abstract String getCreationStatement();
	
	/**
	 * @return
	 * 		The name of the column which serves as the primary key for the 
	 * 		table.
	 */
	public abstract String getPrimaryKeyColumnName();
	
}
