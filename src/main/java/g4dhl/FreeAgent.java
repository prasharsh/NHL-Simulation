package g4dhl;

public class FreeAgent implements IFreeAgent {

	private int freeAgentId;
	private String freeAgentName;
	private String freeAgentPosition;

	private int freeAgentAge;
	private int freeAgentSkating;
	private int freeAgentShooting;
	private int freeAgentChecking;
	private int freeAgentSaving;

	public FreeAgent() {
		freeAgentName = null;
		freeAgentPosition = null;
	}

	private boolean checkIfFreeAgentNameIsNullOrEmpty(String freeAgentName) {
		return freeAgentName == null || freeAgentName.trim().isEmpty();
	}

	private boolean checkIfFreeAgentPositionIsNullOrEmpty(String freeAgentPosition) {
		return freeAgentPosition == null || freeAgentPosition.trim().isEmpty();
	}

	enum Position {
		FORWARD, DEFENSE, GOALIE
	}

	@Override
	public double getFreeAgentStrength() {
		String freeAgentPosition = getFreeAgentPosition().toUpperCase();
		double freeAgentStrength = 0.0;
		FreeAgent.Position position = FreeAgent.Position.valueOf(freeAgentPosition);
		if (position == FreeAgent.Position.FORWARD) {
			freeAgentStrength = getFreeAgentSkating() + getFreeAgentShooting() + (getFreeAgentChecking() / 2.0);
		} else if (position == FreeAgent.Position.DEFENSE) {
			freeAgentStrength = getFreeAgentSkating() + getFreeAgentChecking() + (getFreeAgentShooting() / 2.0);
		} else if (position == FreeAgent.Position.GOALIE) {
			freeAgentStrength = getFreeAgentSkating() + getFreeAgentSaving();
		}

		return freeAgentStrength;
	}

	@Override
	public boolean setFreeAgentId(int freeAgentId) {
		this.freeAgentId = freeAgentId;
		return true;
	}

	@Override
	public boolean setFreeAgentName(String freeAgentName) {
		if (checkIfFreeAgentNameIsNullOrEmpty(freeAgentName))
			return false;
		this.freeAgentName = freeAgentName;
		return true;
	}

	@Override
	public boolean setFreeAgentAge(int freeAgentAge) {
		this.freeAgentAge = freeAgentAge;
		return true;
	}

	@Override
	public boolean setFreeAgentSkating(int freeAgentSkating) {
		this.freeAgentSkating = freeAgentSkating;
		return true;
	}

	@Override
	public boolean setFreeAgentShooting(int freeAgentShooting) {
		this.freeAgentShooting = freeAgentShooting;
		return true;
	}

	@Override
	public boolean setFreeAgentChecking(int freeAgentChecking) {
		this.freeAgentChecking = freeAgentChecking;
		return true;
	}

	@Override
	public boolean setFreeAgentSaving(int freeAgentSaving) {
		this.freeAgentSaving = freeAgentSaving;
		return true;
	}

	@Override
	public boolean setFreeAgentPosition(String freeAgentPosition) {
		if (checkIfFreeAgentPositionIsNullOrEmpty(freeAgentPosition))
			return false;
		this.freeAgentPosition = freeAgentPosition;
		return true;
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
	public String getFreeAgentPosition() {
		return freeAgentPosition;
	}

	@Override
	public int getFreeAgentAge() {
		return freeAgentAge;
	}

	@Override
	public int getFreeAgentSkating() {
		return freeAgentSkating;
	}

	@Override
	public int getFreeAgentShooting() {
		return freeAgentShooting;
	}

	@Override
	public int getFreeAgentChecking() {
		return freeAgentChecking;
	}

	@Override
	public int getFreeAgentSaving() {
		return freeAgentSaving;
	}

}
