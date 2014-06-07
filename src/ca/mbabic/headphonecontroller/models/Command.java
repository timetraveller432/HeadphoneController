package ca.mbabic.headphonecontroller.models;

public class Command {

	private String id;
	private String name;
	
	public Command(String cmdKey, String cmdName) {
		id = cmdKey;
		name = cmdName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
