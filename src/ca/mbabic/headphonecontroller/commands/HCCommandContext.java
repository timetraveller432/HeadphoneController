package ca.mbabic.headphonecontroller.commands;

public class HCCommandContext {

	private HCCommand command;
	
	public HCCommandContext() {
	}
	
	public void execute() {
		command.execute();
	}
	
	public void setCommand(HCCommand cmd) {
		command = cmd;
	}
	
}
