package g4dhl;

public class HeadCoach implements IHeadCoach {

	private int headCoachId;
	private String headCoachName;

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
	public boolean setHeadCoachId(int headCoachId) {
		this.headCoachId = headCoachId;
		return true;
	}

	@Override
	public boolean setHeadCoachName(String headCoachName) {
		if(checkIfHeadCoachNameIsNullOrEmpty(headCoachName)) return false;
		this.headCoachName = headCoachName;
		return true;
	}

}
