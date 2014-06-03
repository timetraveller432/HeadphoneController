package ca.mbabic.headphonecontroller.commands;

/**
 * Command executable by the application given input from the headphones.
 * Implementation of the Strategy Pattern.  Will be useful when command executed
 * by each state in the state machine is dependent on configuration values. 
 * See: http://sourcemaking.com/design_patterns/strategy
 * @author Marko
 */
public interface HCCommand {
	
	public void execute();	
	
}
