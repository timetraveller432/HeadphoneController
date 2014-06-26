package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

public class HCCommandCallStateTable extends HCDbTable {

	public HCCommandCallStateTable() {
		
		TABLE_NAME = "COMMAND_CALLSTATES";
		
		PRIMARY_KEY_NAME = "";
		
		CREATION_STMT = "CREATE TABLE " + TABLE_NAME + "("  		+
				"command_id int, " 									+
				"callstate_id int, " 								+
				"FOREIGN KEY (command_id) REFERENCES COMMAND(id), "	+
				"FOREIGN KEY (callstate_id) REFERENCE CALLSTATE(id)"+
			");";
	
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}
	
	
}
