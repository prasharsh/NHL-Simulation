package leagueModel;

public interface IFreeAgent {
    String getFreeAgentName();
    void setFreeAgentName(String freeAgentName);
    String getFreeAgentPosition();
    void setFreeAgentPosition(String freeAgentPosition);
    boolean isFreeAgentCaptain();
    void setFreeAgentCaptain(boolean freeAgentCaptain);
}
