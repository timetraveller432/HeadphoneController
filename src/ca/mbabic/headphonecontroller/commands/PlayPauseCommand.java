package ca.mbabic.headphonecontroller.commands;

import android.content.Context;
import android.content.Intent;
import ca.mbabic.headphonecontroller.HCApplication;

/**
 * Command executing the play and pausing of media files.
 * @author Marko Babic
 */
public class PlayPauseCommand implements HCCommand {

	
	@Override
	public void execute() {
		Context cxt;
		Intent intent;
		
		cxt = HCApplication.getInstance();
		
		intent= new Intent("com.android.music.musicservicecommand");
		intent.putExtra("command", "togglepause");
		cxt.sendOrderedBroadcast(intent, null);
	}

}
