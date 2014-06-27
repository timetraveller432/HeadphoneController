package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static ca.mbabic.headphonecontroller.configuration.HCConfigConstants.*;

import android.content.ContentValues;

/**
 * Table storing the call states in which a command can be executed.
 * @author Marko Babic
 */
public class HCCommandCallStateTable extends HCDbTable {

	
	public static final String COMMAND_KEY = "command_key";
	public static final String CALLSTATE_ID = "callstate_id";
	
	private HCDbTable cmdTable, callstateTable;
	
	public HCCommandCallStateTable() {
		
		cmdTable = new HCCommandTable();
		
		callstateTable = new HCCallStateTable();
		
		TABLE_NAME = "COMMAND_CALLSTATES";
		
		PRIMARY_KEY_NAME = "";
		
		CREATION_STMT = "CREATE TABLE " + TABLE_NAME + "("  					+
				COMMAND_KEY + " text, " 										+
				CALLSTATE_ID + " int, " 										+
				"FOREIGN KEY (" + COMMAND_KEY + ") REFERENCES " + 
				cmdTable.getTableName() + "(" + cmdTable.getPrimaryKeyColumnName() + "), " +
				"FOREIGN KEY (" + CALLSTATE_ID + ") REFERENCES " +  
				callstateTable.getTableName() + "(" + callstateTable.getPrimaryKeyColumnName() + ")" + 
			");";
			
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {

		ArrayList<ContentValues> ret;
		ContentValues cv;
		int[] validStates;
		int i;
		
		ret = new ArrayList<ContentValues>();
		
		for (String key : CMD_KEYS) {
						
			validStates = VALID_CMD_STATES.get(key);
			
			for (i = 0; i < validStates.length; i++) {
				
				cv = new ContentValues();
				cv.put(COMMAND_DELIMITER, key);
				cv.put(CALLSTATE_ID, validStates[i]);
				ret.add(cv);
				
			}
			
		}
		
		return ret;
		
	}
	
	
}
