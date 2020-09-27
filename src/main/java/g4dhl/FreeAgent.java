package g4dhl;

public class FreeAgent implements IFreeAgent{

    private String freeAgentName;
    private String freeAgentPosition;
    private boolean freeAgentCaptain;

    @Override
    public String getFreeAgentName() {
        return freeAgentName;
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
