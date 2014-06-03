package ca.mbabic.headphonecontroller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends Activity {

	
	private static final int FILTER_PRIORITY = 2147483647;
	
	/**
	 * TextArea displaying string related to the type of input captured
	 * from the attached headphones.
	 */
	private TextView mInputDisplay;
	
	/**
	 * TextArea displaying string related to the type of output being generated
	 * in response to the headphone input.
	 */
	private TextView mOutputDisplay;
	
	/**
	 * Receiver registered to handle media button presses.
	 */
	private MediaButtonReceiver mMediaBtnReceiver = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		AudioManager am;
		
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.activity_home);
		
		mInputDisplay 	= (TextView) findViewById(R.id.input_display);
		mOutputDisplay 	= (TextView) findViewById(R.id.output_display);

		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		
		am.registerMediaButtonEventReceiver(
				new ComponentName(
						this,
						MediaButtonReceiver.class
				)
		);
			
		//registerReceivers();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	/**
	 * Registers BroadCastReceivers listeners capturing input from headphones.
	 */
	private void registerReceivers() {
		
		IntentFilter intentFilter;
		
		if (mMediaBtnReceiver == null) {
			
			mMediaBtnReceiver = new MediaButtonReceiver();
			
		}
		
		intentFilter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
		intentFilter.setPriority(FILTER_PRIORITY);
		
		registerReceiver(
				mMediaBtnReceiver, 
				intentFilter
		);
		
	}

}


