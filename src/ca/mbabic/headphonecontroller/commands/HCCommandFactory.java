package ca.mbabic.headphonecontroller.commands;

import java.lang.reflect.Constructor;

import android.util.Log;

/**
 * HCCommand creation factory.
 * 
 * @author Marko Babic
 * 
 */
public class HCCommandFactory {
	
	public static final String TAG = ".command.HCCommandFactory";

	/**
	 * Create instance of HCCommand given a string corresponding to the class 
	 * name of the particular instance to be created.
	 * @param className
	 * 		Class name (as returned by Class.getName()) for the particular 
	 * 		HCCommand instance to be returned.
	 * @return
	 * @throws ClassNotFoundException if class name does not correspond to a 
	 * constructible class.
	 */
	@SuppressWarnings("unchecked")
	public static HCCommand createInstance(String className)
			throws ClassNotFoundException, NoSuchMethodException, Exception {

		@SuppressWarnings("rawtypes")
		Class cmdClass;
		@SuppressWarnings("rawtypes")
		Constructor constructor;
		HCCommand instance;
		
		cmdClass = Class.forName(className);

		constructor = cmdClass.getConstructor();
		
		try {
			
			instance = (HCCommand) constructor.newInstance();
			
			return instance;
			
		} catch (Exception e) {
			Log.e(TAG, e.toString());
			throw e;
		}
	}

}
