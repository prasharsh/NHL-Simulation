package g4dhl;

public class FreeAgent implements IFreeAgent {

	private int freeAgentId;
	private String freeAgentName;
	private String freeAgentPosition;
	private boolean freeAgentCaptain;

	public FreeAgent() {
		freeAgentName = null;
		freeAgentPosition = null;
		freeAgentCaptain = false;
	}

	private boolean checkIfFreeAgentNameIsNullOrEmpty(String freeAgentName) {
		return freeAgentName == null || freeAgentName.trim().isEmpty();
	}

	private boolean checkIfFreeAgentPositionIsNullOrEmpty(String freeAgentPosition) {
		return freeAgentPosition == null || freeAgentPosition.trim().isEmpty();
	}

	@Override
	public int getFreeAgentId() {
		return freeAgentId;
	}

	@Override
	public String getFreeAgentName() {
		return freeAgentName;
	}

	@Override
	public boolean setFreeAgentId(int freeAgentId) {
		this.freeAgentId = freeAgentId;
		return true;
	}

	@Override
	public boolean setFreeAgentName(String freeAgentName) {
		if(checkIfFreeAgentNameIsNullOrEmpty(freeAgentName)) return false;
		this.freeAgentName = freeAgentName;
		return true;
	}

	@Override
	public String getFreeAgentPosition() {
		return freeAgentPosition;
	}

	@Override
	public boolean setFreeAgentPosition(String freeAgentPosition) {
		if(checkIfFreeAgentPositionIsNullOrEmpty(freeAgentPosition)) return false;
		this.freeAgentPosition = freeAgentPosition;
		return true;
	}

	@Override
	public boolean isFreeAgentCaptain() {
		return freeAgentCaptain;
	}

	@Override
	public boolean setFreeAgentCaptain(boolean freeAgentCaptain) {
		this.freeAgentCaptain = freeAgentCaptain;
		return true;
	}
}
