package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.*;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class HCCommandTable extends HCDbTable {		
	
	/**
	 * Key for display name stored in db.
	 */
	public static final String DISPLAY_NAME_KEY = "display_name";
	
	public HCCommandTable() {
		
		TABLE_NAME = "COMMAND";
		PRIMARY_KEY_NAME = "key";
		CREATION_STMT =
			"CREATE TABLE " + TABLE_NAME + "(" 	+
				PRIMARY_KEY_NAME + " text PRIMARY KEY NOT NULL, " +
				DISPLAY_NAME_KEY + " text NOT NULL"			+
			");";
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {

		ArrayList<ContentValues> ret;
		ContentValues cv;
		
		ret = new ArrayList<ContentValues>();
		
		for (String key : CMD_KEYS) {
			
			cv = new ContentValues();
			
			cv.put(PRIMARY_KEY_NAME, key);
			cv.put(DISPLAY_NAME_KEY, CMD_DISPLAY_NAMES.get(key));
			
			ret.add(cv);
			
		}
				
		return ret;
	}

}
