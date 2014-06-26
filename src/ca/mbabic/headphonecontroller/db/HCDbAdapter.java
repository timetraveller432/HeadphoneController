package ca.mbabic.headphonecontroller.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class HCDbAdapter {

	/**
	 * Reference to database instance to which adapter is connecting.
	 */
	private SQLiteDatabase db;
	
	/**
	 * Reference to HCDbHelper managing database creation.
	 */
	private HCDbHelper dbHelper;
	
	/**
	 * Database file name as stored on device.
	 */
	private static final String dbFilename = "HeadphoneControllerSQLiteDb";
	
	public HCDbAdapter(Context cxt) {
		dbHelper = new HCDbHelper(cxt, dbFilename);
		db = dbHelper.getWritableDatabase();		
	}
	
}
