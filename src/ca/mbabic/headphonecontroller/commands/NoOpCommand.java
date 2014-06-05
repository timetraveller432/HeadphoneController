/*
 * TODO: license
 */
package ca.mbabic.headphonecontroller.commands;

/**
 * Models the "no-op" command -- useful when sequence pressed is not bound to
 * any operation given current state. (e.g., triple-press when a call is in
 * progress).
 * 
 * @author Marko Babic
 * 
 */
public class NoOpCommand implements HCCommand {

	@Override
	public void execute() {

		// Do nothing.
		
	}

}
