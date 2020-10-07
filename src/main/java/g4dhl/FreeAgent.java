package g4dhl;

public class FreeAgent implements IFreeAgent{

    private String freeAgentName;
    private String freeAgentPosition;
    private boolean freeAgentCaptain;

    @Override
    public int getFreeAgentId() {
        return 0;
    }

    @Override
    public String getFreeAgentName() {
        return freeAgentName;
    }

    @Override
    public void setFreeAgentId(int id) {

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
