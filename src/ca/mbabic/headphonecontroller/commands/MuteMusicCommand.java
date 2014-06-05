package ca.mbabic.headphonecontroller.commands;

import ca.mbabic.headphonecontroller.HCApplication;
import android.content.Context;
import android.media.AudioManager;

/**
 * Command which mutes the music stream.
 * 
 * @author Marko Babic
 * 
 */
public class MuteMusicCommand implements HCCommand {

	public static int previousVolume = 0;

	@Override
	public void execute() {

		AudioManager am;
		Context cxt;
		int streamVolume;

		cxt = HCApplication.getInstance();

		am = (AudioManager) cxt.getSystemService(Context.AUDIO_SERVICE);

		streamVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

		if (streamVolume > 0) {

			am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
			previousVolume = streamVolume;

		} else {

			am.setStreamVolume(AudioManager.STREAM_MUSIC, previousVolume,
					AudioManager.FLAG_SHOW_UI);

		}

	}

}
