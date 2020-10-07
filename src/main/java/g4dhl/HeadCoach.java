package g4dhl;

public class HeadCoach implements IHeadCoach{

    private int headCoachId;
    private String headCoachName;

    @Override
    public int getHeadCoachId() {
        return headCoachId;
    }

    @Override
    public String getHeadCoachName() {
        return headCoachName;
    }

    @Override
    public void setHeadCoachId(int headCoachId) {
        this.headCoachId = headCoachId;
    }

    @Override
    public void setHeadCoachName(String headCoachName) {
        this.headCoachName = headCoachName;
    }
}
