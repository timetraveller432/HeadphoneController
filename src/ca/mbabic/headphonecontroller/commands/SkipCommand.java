package ca.mbabic.headphonecontroller.commands;

import android.content.Context;
import android.content.Intent;
import ca.mbabic.headphonecontroller.HCApplication;

/**
 * Command executing the "skip song" command.
 * @author Marko Babic
 */
public class SkipCommand implements HCCommand {

	
	@Override
	public void execute() {
		Context cxt;
		Intent intent;
		
		cxt = HCApplication.getInstance();
		
		intent= new Intent("com.android.music.musicservicecommand");
		intent.putExtra("command", "next");
		cxt.sendOrderedBroadcast(intent, null);
	}

}
