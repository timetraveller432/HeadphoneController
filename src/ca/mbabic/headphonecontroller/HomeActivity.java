package ca.mbabic.headphonecontroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import ca.mbabic.headphonecontroller.services.MediaButtonListenerService;
import ca.mbabic.headphonecontroller.services.MediaButtonReceiver;

public class HomeActivity extends Activity {

	private static final int FILTER_PRIORITY = 2147483647;

	/**
	 * TextArea displaying string related to the type of input captured from the
	 * attached headphones.
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

		// AudioManager am;
		Intent mediaButtonListenerService;

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		mInputDisplay = (TextView) findViewById(R.id.input_display);
		mOutputDisplay = (TextView) findViewById(R.id.output_display);

		mediaButtonListenerService = new Intent(getApplicationContext(),
				MediaButtonListenerService.class);

		startService(mediaButtonListenerService);

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

}
