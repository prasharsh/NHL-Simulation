package g4dhl;

public class FreeAgent implements IFreeAgent {

	private int freeAgentId;
	private String freeAgentName;
	private String freeAgentPosition;
	private boolean freeAgentCaptain;

	@Override
	public int getFreeAgentId() {
		return freeAgentId;
	}

	@Override
	public String getFreeAgentName() {
		return freeAgentName;
	}

	@Override
	public void setFreeAgentId(int freeAgentId) {
		this.freeAgentId = freeAgentId;
	}

	@Override
	public void setFreeAgentName(String freeAgentName) {
		this.freeAgentName = freeAgentName;
	}

	@Override
	public String getFreeAgentPosition() {
		return freeAgentPosition;
	}

	@Override
	public void setFreeAgentPosition(String freeAgentPosition) {
		this.freeAgentPosition = freeAgentPosition;
	}

	@Override
	public boolean isFreeAgentCaptain() {
		return freeAgentCaptain;
	}

	@Override
	public void setFreeAgentCaptain(boolean freeAgentCaptain) {
		this.freeAgentCaptain = freeAgentCaptain;
	}
}
