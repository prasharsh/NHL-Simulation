package g4dhl;

public class HeadCoach implements IHeadCoach {
	private String headCoachName;

	@Override
	public String getHeadCoachName() {
		return headCoachName;
	}

	@Override
	public void setHeadCoachName(String headCoachName) {
		this.headCoachName = headCoachName;
	}
}
