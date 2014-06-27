package ca.mbabic.headphonecontroller.db;

import java.util.ArrayList;

import android.content.ContentValues;

public class HCInputSequenceCommandsTable extends HCDbTable {

	public static final String INPUTSEQUENCE_KEY = "inputsequence_key";
	public static final String COMMAND_KEY = "command_key";
	public static final String CALLSTATE_ID = "callstate_id";
	
	private HCDbTable cmdTable, csTable, isTable;
	
	public HCInputSequenceCommandsTable() {
		
		cmdTable = new HCCommandTable();
		
		csTable = new HCCallStateTable();
		
		isTable = new HCInputSequenceTable();
		
		TABLE_NAME = "INPUTSEQUENCE_COMMANDS";
		
		PRIMARY_KEY_NAME = "";
		
		CREATION_STMT = 
			"CREATE TABLE " + TABLE_NAME + "(" 		+
				INPUTSEQUENCE_KEY + " text, " 			+
				COMMAND_KEY + " text, " 				+
				CALLSTATE_ID + " int, " 				+
				"FOREIGN KEY (" + INPUTSEQUENCE_KEY + ") REFERENCES " +
				isTable.getTableName() + "(" + isTable.getPrimaryKeyColumnName() + "), " +
				"FOREIGN KEY (" + COMMAND_KEY + ") REFERENCES " +
				cmdTable.getTableName() + "(" + cmdTable.getPrimaryKeyColumnName() + "), " +
				"FOREIGN KEY (" + CALLSTATE_ID + ") REFERENCES " + 
				csTable.getTableName() + "(" + csTable.getPrimaryKeyColumnName() + ")" +
			");";
		
		
		
	}

	@Override
	public ArrayList<ContentValues> getDefaultValues() {

		ArrayList<ContentValues> ret;
		ContentValues cv;
		
		ret = new ArrayList<ContentValues>();
		
		
		
		return ret;
	
	}
}
