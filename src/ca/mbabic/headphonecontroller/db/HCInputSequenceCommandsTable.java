package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

public class HCInputSequenceCommandsTable extends HCDbTable {

	public HCInputSequenceCommandsTable() {
		
		TABLE_NAME = "INPUTSEQUENCE_COMMANDS";
		
		PRIMARY_KEY_NAME = "";
		
		CREATION_STMT = 
			"CREATE TABLE " + TABLE_NAME + "(" 		+
				"inputsequence_id int, " 			+
				"command_id int, " 					+
				"callstate_id int, " 				+
				"FOREIGN KEY (inputsequence_id) REFERENCES INPUTSEQUENCE(id), " +
				"FOREIGN KEY (command_id) REFERENCES COMMAND(id), " +
				"FOREIGN KEY (callstate_id) REFERENCES CALLSTATE(id)" +
			");";
		
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}
}
