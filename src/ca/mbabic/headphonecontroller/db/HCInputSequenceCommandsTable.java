package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

public class HCInputSequenceCommandsTable extends HCDbTable {

	public HCInputSequenceCommandsTable() {
		
		TABLE_NAME = "INPUTSEQUENCE_COMMANDS";
		
		PRIMARY_KEY_NAME = "";
		
		CREATION_STMT = 
			"CREATE TABLE " + TABLE_NAME + "(" 		+
				"inputsequence_key text, " 			+
				"command_key text, " 				+
				"callstate_id int, " 				+
				"FOREIGN KEY (inputsequence_key) REFERENCES INPUTSEQUENCE(key), " +
				"FOREIGN KEY (command_key) REFERENCES COMMAND(key), " +
				"FOREIGN KEY (callstate_id) REFERENCES CALLSTATE(id)" +
			");";
		
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {
		return null;
	}
}
