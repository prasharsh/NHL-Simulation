package g4dhl;

public class HeadCoach implements IHeadCoach{
    private String headCoachName;

    @Override
    public int getHeadCoachId() {
        return 0;
    }

    @Override
    public String getHeadCoachName() {
        return headCoachName;
    }

    @Override
    public void setHeadCoachId(int id) {

    }

    @Override
    public void setHeadCoachName(String headCoachName) {
        this.headCoachName = headCoachName;
    }
}
