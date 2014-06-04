package ca.mbabic.headphonecontroller.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.IBinder;
import ca.mbabic.headphonecontroller.statemachine.HCStateMachine;

public class MediaButtonListenerService extends Service {
	
	public static HCStateMachine mStateMachine = HCStateMachine.getInstance();
	
	public static MediaStateChangeReceiver mMediaStateChangeReceiver;
	
	// Service methods.
	@Override
	public void onCreate() {
		
		IntentFilter intentFilter;
		
		super.onCreate();

		mStateMachine.setService(this);
		
		// Register media state change receiver
		mMediaStateChangeReceiver = new MediaStateChangeReceiver();
		mMediaStateChangeReceiver.setService(this);
		
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.android.music.playstatechanged");
		
		registerReceiver(mMediaStateChangeReceiver, intentFilter);
		
		registerAsMediaButtonListener();
	}

	public void registerAsMediaButtonListener() {
		AudioManager am;
		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		am.registerMediaButtonEventReceiver(new ComponentName(this,
				MediaButtonReceiver.class));		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
