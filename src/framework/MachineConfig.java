package framework;

public class MachineConfig {
	
	private String configFilePath;
	
	public MachineConfig(String configFilePath) {
		this.configFilePath = configFilePath;
	}
	
	public String getConfigFilePath() {
		return this.configFilePath;
	}

}
