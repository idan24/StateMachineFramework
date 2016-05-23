import java.io.*;

/**
 * 
 *
 */
public class Machine {
	private State state;
	private MachineConfig config;
	
	public Machine(MachineConfig config, State defaultState) {
		this.config = config;
		try {
			loadCurrentState();
		}
		catch(StateLoadException i) {
			System.out.println("Could not load last saved state for machine. Defining current state as default state.");
			this.state = defaultState;
		}
	}
	
	public void setState(State state) {
		this.state = state;		
    }
	
	public void handleEvent(Event event) {
		this.state.doAction(this, event);
		saveCurrentState();
	}
	
	private void loadCurrentState() throws StateLoadException {
		try {
			FileInputStream fileIn = new FileInputStream(this.config.getConfigFilePath());
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        this.state = (State) in.readObject();
	        in.close();
	        fileIn.close();
		}
		catch(IOException i) {
	        throw new StateLoadException();
		}
		catch(ClassNotFoundException c) {
			throw new StateLoadException();
		}
		catch(ClassCastException c) {
	        throw new StateLoadException();
		}
	}
	
	private void saveCurrentState() {
		try {
			File lastSavedStateFile = new File(this.config.getConfigFilePath());
			if (!lastSavedStateFile.exists()) {
				lastSavedStateFile.createNewFile();
			}
			
			FileOutputStream fileOut = new FileOutputStream(lastSavedStateFile, false);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(this.state);
	        out.close();
	        fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
}

class StateLoadException extends Exception {
	   public StateLoadException(){
	      super();
	   }
	}