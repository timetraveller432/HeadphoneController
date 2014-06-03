package ca.mbabic.headphonecontroller;

import android.app.Application;

public class HCApplication extends Application {
	
	private static HCApplication instance;

	
	public static HCApplication getInstance() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
}
