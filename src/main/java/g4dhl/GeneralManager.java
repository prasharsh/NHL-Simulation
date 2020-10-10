package g4dhl;

public class GeneralManager implements IGeneralManager {

	int generalManagerId;
	private String generalManagerName;

	public GeneralManager() {
		generalManagerName = null;
	}

	private boolean checkIfGeneralManagerNameIsNullOrEmpty(String generalManagerName) {
		return generalManagerName == null || generalManagerName.trim().isEmpty();
	}

	@Override
	public int getGeneralManagerId() {
		return generalManagerId;
	}

	@Override
	public String getGeneralManagerName() {
		return generalManagerName;
	}

	@Override
	public boolean setGeneralManagerId(int generalManagerId) {
		this.generalManagerId = generalManagerId;
		return true;
	}

	@Override
	public boolean setGeneralManagerName(String generalManagerName) {
		if(checkIfGeneralManagerNameIsNullOrEmpty(generalManagerName)) return false;
		this.generalManagerName = generalManagerName;
		return true;
	}
}
