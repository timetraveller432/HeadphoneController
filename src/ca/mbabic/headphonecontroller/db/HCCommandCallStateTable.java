package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

/**
 * Table storing the call states in which a command can be executed.
 * @author Marko Babic
 */
public class HCCommandCallStateTable extends HCDbTable {

	public HCCommandCallStateTable() {
		
		TABLE_NAME = "COMMAND_CALLSTATES";
		
		PRIMARY_KEY_NAME = "";
		
		CREATION_STMT = "CREATE TABLE " + TABLE_NAME + "("  			+
				"command_key text, " 									+
				"callstate_id int, " 									+
				"FOREIGN KEY (command_key) REFERENCES COMMAND(key), "	+
				"FOREIGN KEY (callstate_id) REFERENCE CALLSTATE(id)"	+
			");";
	
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}
	
	
}
