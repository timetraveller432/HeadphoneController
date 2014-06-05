package ca.mbabic.headphonecontroller;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import ca.mbabic.headphonecontroller.configuration.HCConfigAdapter;
import ca.mbabic.headphonecontroller.services.MediaButtonListenerService;

public class HomeActivity extends Activity {

	private static final String CONFIGURE_TABSTRING = "Configure";

	private static final String ABOUT_TABSTRING = "About";

	private static ConfigurationFragment mConfigFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Intent mediaButtonListenerService;
		Bundle args;
		final ActionBar tabBar;

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		// Start media button listener service.
		mediaButtonListenerService = new Intent(getApplicationContext(),
				MediaButtonListenerService.class);

		startService(mediaButtonListenerService);

		// Init UI elements.
		tabBar = getActionBar();
		tabBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mConfigFragment = new ConfigurationFragment();

		tabBar.addTab(tabBar.newTab().setText(CONFIGURE_TABSTRING)
				.setTabListener(new TabListener(mConfigFragment)));

		tabBar.addTab(tabBar.newTab().setText(ABOUT_TABSTRING)
				.setTabListener(new TabListener(new ConfigurationFragment())));

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

	public static class TabListener implements ActionBar.TabListener {

		private final Fragment mFragment;

		public TabListener(Fragment fragment) {
			mFragment = fragment;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {

			if (mFragment != null) {
				ft.replace(R.id.fragment_container, mFragment);
			}

		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.remove(mFragment);
			}
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// Not implemented.
		}

	}

}
