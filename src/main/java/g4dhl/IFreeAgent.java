package g4dhl;

public interface IFreeAgent {
    int getFreeAgentId();
    String getFreeAgentName();

    void setFreeAgentId(int freeAgentId);
    void setFreeAgentName(String freeAgentName);

    String getFreeAgentPosition();
    void setFreeAgentPosition(String freeAgentPosition);
    boolean isFreeAgentCaptain();
    void setFreeAgentCaptain(boolean freeAgentCaptain);
}
