package framework;
/**
 * 
 *
 */
public class Machine {
	private State state;
	private MachineConfig config;
	
	public Machine(MachineConfig config, State defaultState) {
		this.config = config;
		loadCurrentState(defaultState);
	}
	
	public void handleEvent(Event event) {
		this.state = this.state.doAction(event);
		saveCurrentState();
	}
	
	private void loadCurrentState(State defaultState) {
		Object entity = FileUtils.loadEntityFromFile(this.config.getConfigFilePath());
		
		if (entity == null) {
			System.out.println("Could not load last saved state for machine. Defining current state as default state.");
			this.state = defaultState;
			return;
		}
		else {
			try {
		        this.state = (State)entity;
			}
			catch(ClassCastException c) {
				System.out.println("Could not cast last saved state to a valid state object. Defining current state as default state.");
		        this.state = defaultState;
			}
		}
	}
	
	private void saveCurrentState() {
		if (!FileUtils.saveEntityToFile(this.config.getConfigFilePath(), this.state)) {
			System.out.println("Could not save current state to file. Make sure the file is not being used by other programs.");
		}
	}
}