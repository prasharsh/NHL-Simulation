package g4dhl;

public class HeadCoach implements IHeadCoach {

	private int headCoachId;
	private String headCoachName;
	private float headCoachSkating;
	private float headCoachShooting;
	private float headCoachChecking;
	private float headCoachSaving;

	@Override
	public int getHeadCoachId() {
		return headCoachId;
	}

	private boolean checkIfHeadCoachNameIsNullOrEmpty(String headCoachName) {
		return headCoachName == null || headCoachName.trim().isEmpty();
	}

	@Override
	public String getHeadCoachName() {
		return headCoachName;
	}

	@Override
	public float getHeadCoachSkating() {
		return headCoachSkating;
	}

	@Override
	public float getHeadCoachShooting() {
		return headCoachShooting;
	}

	@Override
	public float getHeadCoachChecking() {
		return headCoachChecking;
	}

	@Override
	public float getHeadCoachSaving() {
		return headCoachSaving;
	}

	@Override
	public boolean setHeadCoachId(int headCoachId) {
		this.headCoachId = headCoachId;
		return true;
	}

	@Override
	public boolean setHeadCoachName(String headCoachName) {
		if (checkIfHeadCoachNameIsNullOrEmpty(headCoachName))
			return false;
		this.headCoachName = headCoachName;
		return true;
	}

	@Override
	public boolean setHeadCoachSkating(float headCoachSkating) {
		this.headCoachSkating = headCoachSkating;
		return true;
	}

	@Override
	public boolean setHeadCoachShooting(float headCoachShooting) {
		this.headCoachShooting = headCoachShooting;
		return true;
	}

	@Override
	public boolean setHeadCoachChecking(float headCoachChecking) {
		this.headCoachChecking = headCoachChecking;
		return true;
	}

	@Override
	public boolean setHeadCoachSaving(float headCoachSaving) {
		this.headCoachSaving = headCoachSaving;
		return true;
	}

}
